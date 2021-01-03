package com.happylife.core.examples.beanpostprocessor;

public class PostProcessorConstants {
    public enum HandlerType{
        TYPE1(1), TYPE2(2);
        private Integer type;
        HandlerType(Integer type){
            this.type = type;
        }
        public Integer getType(){
            return this.type;
        }
    }
}
