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
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                counts.computeIfPresent(MethodAnnotation.class, (k, v) -> ++v);
            }
            for (Parameter parameter : method.getParameters()) {
                if (parameter.isAnnotationPresent(ParameterAnnotation.class)) {
                    counts.computeIfPresent(ParameterAnnotation.class, (k, v) -> ++v);
                }
            }
        }
        countMethodAndParameters(clazz.getSuperclass());
    }

    public Map<Class<?>, Integer> getCounts() {
        return counts;
    }
}
