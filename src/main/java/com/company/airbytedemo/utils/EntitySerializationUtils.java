package com.company.airbytedemo.utils;

import io.jmix.core.EntitySerialization;
import io.jmix.core.Metadata;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class EntitySerializationUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EntitySerializationUtils.applicationContext = applicationContext;
    }

    public static EntitySerialization getEntitySerialization() {
        if (applicationContext != null) {
            try {
                return applicationContext.getBean(EntitySerialization.class);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static boolean isAvailable() {
        return applicationContext != null && getEntitySerialization() != null;
    }

    public static Metadata getMetaData() {
        if (applicationContext != null) {
            try {
                return applicationContext.getBean(Metadata.class);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
