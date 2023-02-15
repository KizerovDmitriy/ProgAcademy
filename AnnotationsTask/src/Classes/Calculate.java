package Classes;

import Annotations.MethodAnnotation;
import Annotations.ParameterAnnotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class Calculate {

    private Integer countMethod = 0;
    private Integer countParameters = 0;
    private final Map<Class<?>, Integer> counts = new HashMap<>();


    public void countMethodAndParameters(Class<?> clazz) {
        if (clazz == Object.class) {
            counts.put(MethodAnnotation.class, countMethod);
            counts.put(ParameterAnnotation.class, countParameters);
            return;
        }
        for (Method method1 : clazz.getDeclaredMethods()) {
            if (method1.isAnnotationPresent(MethodAnnotation.class)) {
                countMethod++;
            }
            for (Parameter parameter : method1.getParameters()) {
                if (parameter.isAnnotationPresent(ParameterAnnotation.class)) {
                    countParameters++;
                }
            }
        }
        countMethodAndParameters(clazz.getSuperclass());
    }

    public Map<Class<?>, Integer> getCounts() {
        return counts;
    }
}