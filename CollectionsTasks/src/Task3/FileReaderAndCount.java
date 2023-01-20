package Task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FileReaderAndCount {
    private final Map<Character, Integer> result = new TreeMap<>();

    public void readAndCount(String... filePath) {
        if (filePath != null && filePath.length != 0) {
            for (String s : filePath) {
                try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
                    int tmp;
                    while ((tmp = reader.read()) != -1) {
                        if (result.containsKey((char) tmp)) {
                            int x = result.get((char) tmp) + 1;
                            result.put((char) tmp, x);
                        } else {
                            result.put((char) tmp, 1);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("File not found");
                }
                result.entrySet().stream().
                        sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
            }
        } else
            System.out.println("Input invalid path");
    }
}