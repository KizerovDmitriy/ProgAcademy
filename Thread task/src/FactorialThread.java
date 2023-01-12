import java.math.BigInteger;

public class FactorialThread extends Thread {
    private final int name;

    public FactorialThread(int name) {
        this.name = name;
    }

    @Override
    public void run() {
        getFactorial();
    }

    public void getFactorial() {
        BigInteger result = null;
        for (int i = 0; i <= this.name; i++) {
            if (i == 0) {
                result = BigInteger.ONE;
            } else {
                result = result.multiply(BigInteger.valueOf(i));
            }
        }
        System.out.println(this.name + ": " + result);
    }
}