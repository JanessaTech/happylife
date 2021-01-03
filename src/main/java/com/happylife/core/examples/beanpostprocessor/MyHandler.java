package com.happylife.core.examples.beanpostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MyHandler {
    final Logger logger = LoggerFactory.getLogger(MyHandler.class);
    void handle();
}
