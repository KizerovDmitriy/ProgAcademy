package classes;

import annotations.Save;
import annotations.SaveTo;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Saver {

    private boolean checkClassName(String name) {
        List<String> javaClassNames = Arrays.asList("Integer", "Double", "String", "Float", "Character");
        return !javaClassNames.contains(name);
    }

    private String serialize(StringBuilder sb, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                StringJoiner sj = new StringJoiner("=");
                field.setAccessible(true);
                if (checkClassName(field.getType().getSimpleName())) {
                    try {
                        serialize(sb, field.get(object));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    sj.add(field.getName());
                    try {
                        sj.add(field.get(object).toString());
                        sb.append(sj).append("\n");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return sb.toString();
    }

    public void serializeObject(Object object, String path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
            bf.write(serialize(sb, object));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deserializeObject(Object object, String path) {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, String> data = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            while (bf.ready()) {
                String[] tmp = bf.readLine().split("=");
                data.put(tmp[0], tmp[1]);
            }
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    if (checkClassName(field.getType().getSimpleName())) {
                        Object myObj = field.getType().newInstance();
                        deserializeObject(myObj, path);
                        field.set(object,myObj);
                    } else {
                        for (Map.Entry<String, String> entry : data.entrySet()) {
                            if (entry.getKey().equals(field.getName())) {
                                field.set(object, entry.getValue());
                            }
                        }
                    }
                } else {
                    field.setAccessible(true);
                    field.set(object, null);
                }
            }
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToFile(Class<?> clazz) {
        if (clazz.isAnnotationPresent(SaveTo.class)) {
            SaveTo saveTo = clazz.getAnnotation(SaveTo.class);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(annotations.Saver.class)) {
                    try {
                        Object myObj = clazz.newInstance();
                        method.invoke(myObj, saveTo.path());
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}