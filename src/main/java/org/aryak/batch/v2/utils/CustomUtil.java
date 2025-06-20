package org.aryak.batch.v2.utils;

import org.joor.Reflect;

public class CustomUtil {
    public static void setNestedProperty(Object root, String path, String value) throws NoSuchFieldException {
        String[] parts = path.split("\\.");
        Object current = root;
        for (int i = 0; i < parts.length - 1; i++) {
            String getter = "get" + capitalize(parts[i]);
            String setter = "set" + capitalize(parts[i]);
            Object nested = Reflect.on(current).call(getter).get();

            if (nested == null) {
                Class<?> type = Reflect.on(current).type().getDeclaredField(parts[i]).getType();
                Object newInstance = Reflect.on(type).create().get();
                Reflect.on(current).call(setter, newInstance);
                nested = newInstance;
            }
            current = nested;
        }

        // Set final value
        String finalSetter = "set" + capitalize(parts[parts.length - 1]);
        Reflect.on(current).call(finalSetter, value);
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String getNestedProperty(Object root, String path) throws Exception {
        String[] parts = path.split("\\.");
        Object current = root;

        for (String part : parts) {
            String getter = "get" + capitalize(part);
            current = Reflect.on(current).call(getter).get();
            if (current == null) {
                return null;
            }
        }

        return current.toString(); // Always returns string (as per your requirement)
    }

}
