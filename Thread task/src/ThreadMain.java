import java.util.Random;


public class ThreadMain {
    public static void main(String[] args) {

        // factorial
        /*for (int i = 0; i <= 100; i++) {
            new FactorialThread(i).start();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/

        // arrays
        // create array
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(0, 10);
        }

        // thread algorithm
        ArrayThread thread1 = new ArrayThread(0, 5, array);
        ArrayThread thread2 = new ArrayThread(5, 10, array);
        long threadSort = System.nanoTime();
        thread1.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread2.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long sum = thread1.getResult() + thread2.getResult();
        threadSort = System.nanoTime() - threadSort;
        System.out.printf("Thread algorithm: %,9.3f ms \n", threadSort / 1_000_000.0);
        System.out.println(sum);

        //standard algorithm
        long standardSort = System.nanoTime();
        long result = 0;
        for (int j : array) {
            result += j;
        }
        standardSort = System.nanoTime() - standardSort;
        System.out.printf("Standard algorithm: %,9.3f ms \n", standardSort / 1_000_000.0);
        System.out.println(result);
    }
}