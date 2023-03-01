package testpackage.inner;

import annotation.Work;

public class InnerTest1 {
    @Work
    private void test1() {
        System.out.println("Method from " + getClass().getSimpleName());
    }

    private void test2() {
        System.out.println("Method from " + getClass().getSimpleName());
    }

    private void test3() {
        System.out.println("Method from " + getClass().getSimpleName());
    }
}
