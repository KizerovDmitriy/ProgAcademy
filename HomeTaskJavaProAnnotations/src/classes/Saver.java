package classes;

import annotations.Save;
import annotations.SaveTo;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Saver {

    public void serializeObject(Object object, String path) {
        Field[] fields = object.getClass().getDeclaredFields();
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    StringJoiner sj = new StringJoiner("=");
                    field.setAccessible(true);
                    sj.add(field.getName());
                    try {
                        sj.add(field.get(object).toString());
                        bf.write(sj.toString());
                        bf.newLine();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
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
                    for (Map.Entry<String, String> entry : data.entrySet()) {
                        if (entry.getKey().equals(field.getName())) {
                            field.set(object, entry.getValue());
                        }
                    }
                } else {
                    field.setAccessible(true);
                    field.set(object, null);
                }
            }
        } catch (IOException | IllegalAccessException e) {
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