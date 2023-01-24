package translator;

import dictionary.UkrainianToEnglishDictionary;

import java.io.*;
import java.util.StringJoiner;

public class TranslateAndWrite {
    private UkrainianToEnglishDictionary dictionary;
    private String outputFilePath;

    public TranslateAndWrite(String outputFilePath, UkrainianToEnglishDictionary dictionary) {
        if (outputFilePath != null) {
            this.outputFilePath = outputFilePath;
            this.dictionary = dictionary;
        }
    }

    public void translate(String inputFile) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (inputFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                String[] engVersion = reader.readLine().toLowerCase().split(" ");
                for (String s : engVersion) {
                    if (dictionary.getDictionary().containsKey(s)) {
                        stringJoiner.add(dictionary.getDictionary().get(s));
                    }
                }
                writer.write(stringJoiner.toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Invalid input data");
        }
    }
}