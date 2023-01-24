package dictionary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class UkrainianToEnglishDictionary {

    private final Map<String, String> dictionary = new TreeMap<>();


    public void addWordToDictionary(String eng, String ukr) {
        dictionary.put(eng, ukr);
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public void saveDictionary(String fileName) {
        if (fileName != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue());
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}