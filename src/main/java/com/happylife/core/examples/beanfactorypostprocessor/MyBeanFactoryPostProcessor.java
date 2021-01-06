package com.happylife.core.examples.beanfactorypostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private Logger logger = LoggerFactory.getLogger(MyBeanFactoryPostProcessor.class);
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("enter MyBeanFactoryPostProcessor.postProcessBeanFactory");
        String[] names = beanFactory.getBeanDefinitionNames();
        for(String name: names){
            if(name.equals("MyBeanDefinition")){
                BeanDefinition def = beanFactory.getBeanDefinition(name);
                MutablePropertyValues m = def.getPropertyValues();
                m.addPropertyValue("name", "juan");
                logger.info("set the value of name for MyBeanDefinition to be juan");
            }
        }

    }
}
