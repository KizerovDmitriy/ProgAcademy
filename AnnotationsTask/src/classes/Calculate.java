package classes;

import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class Calculate {

    private final Map<Class<?>, Integer> counts = new HashMap<>();

    public Calculate() {
        counts.put(MethodAnnotation.class, 0);
        counts.put(ParameterAnnotation.class, 0);
    }

    public void countMethodAndParameters(Class<?> clazz) {
        if (clazz == Object.class) {
            return;
        }
        for (Method method : clazz.getDeclaredMethods()) {
            int count;
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                count = counts.get(MethodAnnotation.class);
                counts.put(MethodAnnotation.class, ++count);
            }
            for (Parameter parameter : method.getParameters()) {
                if (parameter.isAnnotationPresent(ParameterAnnotation.class)) {
                    count = counts.get(ParameterAnnotation.class);
                    counts.put(ParameterAnnotation.class, ++count);
                }
            }
        }
        countMethodAndParameters(clazz.getSuperclass());
    }

    public Map<Class<?>, Integer> getCounts() {
        return counts;
    }
}