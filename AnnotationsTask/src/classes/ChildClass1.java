package classes;

import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;

public class ChildClass1 extends SuperClass {

    @MethodAnnotation
    void walk() {
    }

    private String saySomething(@ParameterAnnotation String str) {
        return "Hello";
    }

    protected boolean trueOrFalse(boolean type) {
        return false;
    }

    @MethodAnnotation
    public void doJob() {
    }
}