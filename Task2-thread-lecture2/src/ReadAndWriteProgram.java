import Buffer.BufferCache;
import ReadThread.ReadCache;
import WriteThread.WriteToCache;

public class ReadAndWriteProgram {
    public static void main(String[] args) {
        String sourceFile = "D:\\1.txt";
        String destinationFile = "D:\\2.txt";

        BufferCache bufferCache = new BufferCache();
        ReadCache readCache = new ReadCache(sourceFile, bufferCache);
        WriteToCache writeToCache = new WriteToCache(destinationFile, bufferCache);

        readCache.start();
        writeToCache.start();
    }
}