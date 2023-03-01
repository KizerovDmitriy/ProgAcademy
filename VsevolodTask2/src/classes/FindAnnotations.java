package classes;

import annotation.Work;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FindAnnotations {
    /**
     *  invoke all methods in Class who has @Work annotation
     * @param packageName package name
     */
    public void invokeMethodsWithAnnotation(String packageName) {
        List<Class<?>> classArray = getClasses(packageName);
        for (Class<?> clazz : classArray) {
            Method[] methods = clazz.getDeclaredMethods();
            try {
                if (clazz.isAnnotationPresent(Work.class)) {
                    for (Method method : methods) {
                        method.setAccessible(true);
                        method.invoke(clazz.newInstance());
                    }
                } else {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Work.class)) {
                            method.setAccessible(true);
                            method.invoke(clazz.newInstance());
                        }
                    }
                }
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * return array with all classes in package
     * @param path package name
     * @return List of all classes in current package
     */
    private List<Class<?>> getClasses(String path) {
        List<Class<?>> result = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String pathName = path.replace(".", "/");
        try {
            Enumeration<URL> resources = classLoader.getResources(pathName);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    File[] files = directory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            String fileName = file.getName();
                            if (fileName.endsWith(".class")) {
                                String className = (pathName + "." + fileName.substring(0, fileName.length() - 6)).replaceAll("/", ".");
                                result.add(Class.forName(className));
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}