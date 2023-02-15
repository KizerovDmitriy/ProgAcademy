package classes;

import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;

public class SuperClass {
    @MethodAnnotation
    public void doSomeWork() {
    }

    @MethodAnnotation
    private String reverseString(@ParameterAnnotation String str) {
        return "";
    }

    protected int sum(@ParameterAnnotation int a, int b) {
        return a + b;
    }

    void doJob(@ParameterAnnotation int a, @ParameterAnnotation float b, @ParameterAnnotation boolean c) {
    }

    @MethodAnnotation
    private void run(Object obj) {
    }
}