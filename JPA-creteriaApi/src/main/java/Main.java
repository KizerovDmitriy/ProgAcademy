import org.example.restaurant.MenuDao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (MenuDao dao = new MenuDao()) {
            try (Scanner sc = new Scanner(System.in)) {
                while (true) {
                    System.out.println("1: add new dish");
                    System.out.println("2: criteria by price");
                    System.out.println("3: criteria - only with discount");
                    System.out.println("4: criteria - weight less than kilogram");
                    System.out.print("Input your choice --> ");
                    String choice = sc.nextLine();

                    switch (choice) {
                        case "1" -> dao.addNewDish(sc);
                        case "2" -> dao.sortByPrice();
                        case "3" -> dao.onlyDiscount();
                        case "4" -> dao.lessThanKilo();
                        default -> {
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something go wrong");
        }
    }
}