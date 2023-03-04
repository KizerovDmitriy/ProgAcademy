package testpackage;

import annotation.Work;

@Work
public class Test2 {

    private void test1(int age) {
        System.out.println("Method from " + getClass().getSimpleName());
    }

    private void test2() {
        System.out.println("Method from " + getClass().getSimpleName());
    }

    private void test3() {
        System.out.println("Method from " + getClass().getSimpleName());
    }
}
