package mainprograms;


import annotations.Test;
import classes.TestAnnotationClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CallMethodProgram {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = TestAnnotationClass.class;
        Method[] methods = clazz.getMethods();
        TestAnnotationClass annotationClass = new TestAnnotationClass();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test an = method.getAnnotation(Test.class);
                method.invoke(annotationClass, an.param1(), an.param2());
            }
        }
    }
}