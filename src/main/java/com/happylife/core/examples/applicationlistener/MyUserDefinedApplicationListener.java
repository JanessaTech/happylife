package com.happylife.core.examples.applicationlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class MyUserDefinedApplicationListener implements ApplicationListener<MyUserDefinedEvent> {
    final static private Logger logger = LoggerFactory.getLogger(MyUserDefinedApplicationListener.class);
    @Override
    public void onApplicationEvent(MyUserDefinedEvent event) {
        logger.info("received msg is:" + event.getMsg());

    }
}
