package Task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FileReaderAndCount {
    private final Map<Character,Integer> result = new TreeMap<>();

    public void readAndCount (String filePath){

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while (reader.read() != -1){
                int tmp = reader.read();
                if (result.containsKey((char) tmp)){
                    result.computeIfPresent((char) tmp,(k,v)->++v);
                }
                else {
                    result.put((char) tmp, 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }
}