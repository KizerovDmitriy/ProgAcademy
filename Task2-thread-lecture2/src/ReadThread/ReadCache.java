package ReadThread;

import Buffer.BufferCache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCache extends Thread {
    private String fileName;
    private BufferCache bufferCache;

    public ReadCache(String fileName, BufferCache bufferCache) {
        this.fileName = fileName;
        this.bufferCache = bufferCache;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()){
                String temp = reader.readLine();
                bufferCache.setCache(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bufferCache.setStop(true);
    }
}