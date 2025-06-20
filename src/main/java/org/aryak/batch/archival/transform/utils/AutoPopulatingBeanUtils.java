package org.aryak.batch.archival.transform.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.NestedNullException;

import java.lang.reflect.InvocationTargetException;

public class AutoPopulatingBeanUtils extends BeanUtilsBean {
    @Override
    public void setProperty(Object bean, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        try {
            super.setProperty(bean, name, value);
        } catch (NestedNullException e) {
            try {
                createPath(bean, name);
                super.setProperty(bean, name, value);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to auto-populate nested property: " + name, ex);
            }
        }
    }

    private void createPath(Object bean, String path)
            throws Exception {
        String[] parts = path.split("\\.");
        Object current = bean;

        for (int i = 0; i < parts.length - 1; i++) {
            String part = parts[i];

            Object nested = super.getPropertyUtils().getSimpleProperty(current, part); // FIXED LINE
            if (nested == null) {
                Class<?> type = super.getPropertyUtils().getPropertyType(current, part);
                Object newInstance = type.getDeclaredConstructor().newInstance();
                super.setProperty(current, part, newInstance);
                nested = newInstance;
            }

            current = nested;
        }
    }
}
