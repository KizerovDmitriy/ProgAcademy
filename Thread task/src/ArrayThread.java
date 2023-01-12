public class ArrayThread extends Thread {
    private final int[] threadArray;
    private long result;
    private final int start;
    private final int end;

    public ArrayThread(int start, int end, int[] threadArray) {
        this.start = start;
        this.end = end;
        this.threadArray = threadArray;
    }

    @Override
    public void run() {
        this.result = sumElements();
    }

    private long sumElements(){
        long result = 0;
        for (int i = start; i < end; i++) {
            result += threadArray[i];
        }
        return result;
    }

    public long getResult() {
        return result;
    }
}