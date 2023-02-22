package classes;

import annotations.Saver;
import annotations.SaveTo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "D:\\HomeTaskJavaProAnnotations\\src\\resource\\textsave.txt")
public class TextContainer {
    String text = "Test String";

    @Saver
    public void save(String path) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
            bf.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}