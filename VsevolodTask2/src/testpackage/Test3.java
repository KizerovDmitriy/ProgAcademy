package testpackage;

import annotation.Work;

public class Test3 {
    @Work
    private void test1() {
        System.out.println("Method from " + getClass().getSimpleName());
    }

    private void test2() {
        System.out.println("Method from " + getClass().getSimpleName());
    }

    @Work
    private void test3() {
        System.out.println("Method from " + getClass().getSimpleName());
    }
}
