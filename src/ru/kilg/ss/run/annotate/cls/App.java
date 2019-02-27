package ru.kilg.ss.run.annotate.cls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName(Sample.class.getName());
            Object obj = cls.getDeclaredConstructor().newInstance();
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(SayIt.class)) {
                    SayIt annotation = method.getAnnotation(SayIt.class);
                    method.setAccessible(true);
                    method.invoke(obj, (!annotation.value().isEmpty()) ? annotation.value() : null);
                }
            }
        } catch (ClassNotFoundException    |
                 IllegalAccessException    |
                 InstantiationException    |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
