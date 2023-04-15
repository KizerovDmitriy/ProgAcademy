package com.example.telegrambot;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MySuperBot extends TelegramLongPollingBot {

    private final static int USER_COUNT = 100;

    private final BotCredentials botCredentials;
    private final UserService userService;

    public MySuperBot(TelegramBotsApi telegramBotsApi,
                      BotCredentials botCredentials,
                      UserService userService) {
        super(botCredentials.getBotToken());

        this.botCredentials = botCredentials;
        this.userService = userService;

        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText())
            return;

        String text = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        Optional<User> userOpt = userService.findUserByChatId(chatId);
        User user;

        if (userOpt.isPresent()) {
            user = userOpt.get();

            if (isAdminCommand(text) && user.getAdmin()) {
                if (text.startsWith("/broadcast") || text.startsWith("/broadcast ")) {
                    processAdminCommand(chatId, text);
                }

                // set admin to user

                if (text.startsWith("/setadmin") || text.startsWith("/setadmin ")){
                    setAdminCommand(text, chatId);
                    return;
                }

                //link to delete users

                if (text.startsWith("/delete") || text.startsWith("/delete ")){
                    sendMessage(chatId,"http://localhost:8080/users");
                    return;
                }
                return;
            }

            // delete user
            if (isDeleteCommand(text)){
                deleteUser(chatId);
                return;
            }

            // pattern State
            if (user.getState() == 1) {
                boolean valid = Utils.isValidUkrainianPhoneNumber(text);

                if (valid) {
                    user.setPhone(text);
                    sendMessage(chatId, "Thanks! What is your name?");
                    user.incrementState();
                } else {
                    sendMessage(chatId, "Wrong phone number! Try again.");
                }
            } else if (user.getState() == 2) {
                user.setName(text);
                sendMessage(chatId, "Thanks!");
                user.incrementState();
            }
            userService.updateUser(user);
        } else {
            helpMessage(chatId);
            System.out.println(text);

            addNewUser(text, chatId);
        }
    }

    /**
     * use only for real URl, not local
     * @param chatId
     * @param link
     */
    public void sendWebsiteLink(long chatId, String link) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Перейти на сайт");
        inlineKeyboardButton.setUrl(link);
        row.add(inlineKeyboardButton);
        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ваша ссылка:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void helpMessage(long chatId) {
        sendMessage(chatId, """
                You can control me by sending these commands:
                /delete - delete your account;
                /broadcast - admin command to send some message to all users;
                /setadmin - Give the user administrator rights by id;
                """);
    }

    private void deleteUser(long chatId) {
        userService.deleteUser(chatId);
        sendMessage(chatId,"Your account has been successfully deleted");
        sendImage(chatId,new InputFile("https://i.ytimg.com/vi/l1A5QkhmLoE/maxresdefault.jpg"));
    }

    private void addNewUser(String text, long chatId) {
        User user;
        String[] parts = text.split(" ");
        String password = (parts.length == 2) ? parts[1] : "";

        sendMessage(chatId, "Hello, I'm " + botCredentials.getBotName());
        sendImage(chatId,new InputFile("https://cdn.27.ua/sc--media--prod/default/16/e9/82/16e98218-1842-4677-a580-138bbb614621.jpg"));
        sendMessage(chatId, "What is your phone number (38XXYYYYYYY)?");

        user = new User();
        user.setAdmin(isValidPassword(password));
        user.setChatId(chatId);
        user.setState(1L);

        userService.saveUser(user);
    }

    private void setAdminCommand(String text, long chatId) {
        String[] parts = text.split(" ");
        if (parts.length == 2) {
            String userId = parts[1];
            setAdminById(userId, chatId);
        } else {
            sendMessage(chatId, "Invalid command format. Usage: /setadminbyid [user_id]");
        }
    }

    private void setAdminById(String userId, long chatId) {
        var adminOpt = userService.findUserById(Long.parseLong(userId));
        if (adminOpt.isPresent()) {
            User adminUser = adminOpt.get();
            adminUser.setAdmin(true);
            userService.updateUser(adminUser);
            sendMessage(chatId, "User with ID " + userId + " has been granted administrator rights.");
        }
        else {
            sendMessage(chatId, "User not found.");
        }
    }

    private boolean isDeleteCommand(String text) {
        return text.startsWith("/delete ") || text.startsWith("/delete");
    }


    private boolean isValidPassword(String password) {
        return "1".equals(password);
    }

    // /broadcast message cvdfgd
    private void processAdminCommand(long chatId, String text) {
        int idx = text.indexOf(' ');
        if (idx < 0) {
            sendMessage(chatId, "Wrong admin command!");
            return;
        }

        String message = text.substring(idx + 1);

        long usersCount = userService.getUsersCount();
        long pagesCount = (usersCount / USER_COUNT) +
                ((usersCount % USER_COUNT > 0) ? 1 : 0);

        for (int i = 0; i < pagesCount; i++) {
            List<User> users = userService.findAllUsers(
                    PageRequest.of(i, USER_COUNT)
            );
            users.stream().filter(user -> user.getAdmin().equals(false)).forEach(user -> sendMessage(user.getChatId(), message));
        }
    }

    private boolean isAdminCommand(String text) {
        return text.startsWith("/broadcast ") || text.startsWith("/setadmin ") || text.startsWith("/delete");
    }

    private void sendMessage(long chatId, String message) {
        var msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendImage(long chatId, InputFile image){
        var img = new SendPhoto();
        img.setChatId(chatId);
        img.setPhoto(image);
        try {
            execute(img);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botCredentials.getBotName();
    }
}