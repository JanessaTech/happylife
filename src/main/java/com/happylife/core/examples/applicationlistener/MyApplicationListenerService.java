package com.happylife.core.examples.applicationlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * A demo shows how to use user-defined application listener
 * There is one problem remaining: how spring figures out which listeners it should choose to send event?
 * if the mismatched listener is chosen, ClassCastException will throw.
 * see {@link SimpleApplicationEventMulticaster#multicastEvent} on how spring figure matched listener
 */
@RestController
@RequestMapping("/tuoke-web/api/examples/listener")
public class MyApplicationListenerService {
    @Autowired
    @Qualifier(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    private SimpleApplicationEventMulticaster simpleApplicationEventMulticaster;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public ResponseEntity<Object> sendMsg(@RequestParam(value = "name", required = true) String name){
        /**
         * I am using two ways to publish event, both are OK:
         * one using simpleApplicationEventMulticaster, one using applicationContext
         * I know when simpleApplicationEventMulticaster is registered into spring context。 see {@link AbstractApplicationContext #initApplicationEventMulticaster}
         * but I didn't find when applicationContext is registered so far .. annoying
         */
        applicationContext.publishEvent(new MyUserDefinedEvent(this, name));
        //simpleApplicationEventMulticaster.multicastEvent(new MyUserDefinedEvent(this, name));
        return new ResponseEntity<>("send event successfully", HttpStatus.OK);
    }
}
