package com.happylife.core.examples.beanpostprocessor;

@Handler(handleType = PostProcessorConstants.HandlerType.TYPE2)
public class Type2MyHandler implements MyHandler{
    @Override
    public void handle() {
        logger.info("This is Type2MyHandler");
    }
}
