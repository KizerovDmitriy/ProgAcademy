package WriteThread;

import Buffer.BufferCache;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToCache extends Thread {
    private String fileName;
    private BufferCache bufferCache;

    public WriteToCache(String fileName, BufferCache bufferCache) {
        this.fileName = fileName;
        this.bufferCache = bufferCache;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            while (!bufferCache.isStop()){
                String temp = bufferCache.getCache();
                writer.write(temp);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}