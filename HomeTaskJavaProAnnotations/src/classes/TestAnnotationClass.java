package classes;

public class TestAnnotationClass {
    @annotations.Test(param1 = 2, param2 = 5)
    public void test(int value1, int value2) {
        System.out.println(value1 + value2);
    }
}