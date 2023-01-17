package Buffer;

public class BufferCache {
    private boolean isBusy = false;
    private boolean stop = false;
    private String cache = "";

    public synchronized String getCache() {
        while (!isBusy) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String temp = this.cache;
        isBusy = false;
        notifyAll();
        System.out.println("Write line: " + this.cache);
        return temp;
    }

    public synchronized void setCache(String text) {
        while (isBusy) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.cache = text;
        isBusy = true;
        System.out.println("Read line: " + this.cache);
        notifyAll();
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}