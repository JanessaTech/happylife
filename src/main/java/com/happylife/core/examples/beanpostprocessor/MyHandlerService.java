package com.happylife.core.examples.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A demo shows how to use BeanPostProcessor
 */
@RestController
@RequestMapping("/tuoke-web/api/examples/beanPost")
@Service
public class MyHandlerService implements BeanPostProcessor {
    private Map<Integer, MyHandler> map = new HashMap<>();
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MyHandler){
            Handler handler = bean.getClass().getAnnotation(Handler.class);
            if(handler != null){
                map.put(handler.handleType().getType(),(MyHandler)bean);
            }
        }
        return bean;
    }

    @GetMapping("/test/{type}")
    public ResponseEntity<Object> handle(@PathVariable("type") int type){
        MyHandler myHandler= map.get(type);
        myHandler.handle();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
