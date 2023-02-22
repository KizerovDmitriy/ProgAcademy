package mainprograms;

import classes.Saver;
import classes.TextContainer;

public class SaveStringToFileProgram {
    public static void main(String[] args) {
        Saver saver = new Saver();
        saver.saveToFile(TextContainer.class);
    }
}