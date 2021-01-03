package com.happylife.core.examples.beanpostprocessor;


@Handler(handleType = PostProcessorConstants.HandlerType.TYPE1)
public class Type1MyHandler implements MyHandler {
    @Override
    public void handle() {
        logger.info("this is Type1MyHandler");
    }
}
