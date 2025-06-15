package org.aryak.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.v2.GenericMapReaderFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DynamicBeanRegistrar {

    private final ApplicationContext context;

    public DynamicBeanRegistrar(ApplicationContext context) {
        this.context = context;
    }

    public void registerClientReader(String beanName, int id, String delimiter, String path, List<String> names) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)
                ((ConfigurableApplicationContext) context).getBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(GenericMapReaderFactory.class)
                .addConstructorArgValue(id)
                .addConstructorArgValue(delimiter)
                .addConstructorArgValue(path)
                .addConstructorArgValue(names);

        //var beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        var beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        log.info("Bean for client {} has been registered. Previous : {} New Count : {}", beanName, beanDefinitionCount, beanFactory.getBeanDefinitionCount());
    }
}

