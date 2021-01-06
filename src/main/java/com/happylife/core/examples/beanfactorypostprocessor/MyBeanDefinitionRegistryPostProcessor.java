package com.happylife.core.examples.beanfactorypostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, Ordered {
    private Logger logger = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        logger.info("enter MyBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
        RootBeanDefinition def = new RootBeanDefinition(MyBeanDefinition.class);
        // in fact, we don't need to set this value here.
        // because the default value for role is BeanDefinition.ROLE_APPLICATION
        // this code is just a reminder of there being such a setter
        def.setRole(BeanDefinition.ROLE_APPLICATION);
        registry.registerBeanDefinition("MyBeanDefinition", def);
        logger.info("register MyBeanDefinition successfully");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
