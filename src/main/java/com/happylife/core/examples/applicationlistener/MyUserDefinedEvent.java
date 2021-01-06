package com.happylife.core.examples.applicationlistener;

import org.springframework.context.ApplicationEvent;

public class MyUserDefinedEvent extends ApplicationEvent {
    private String msg;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyUserDefinedEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
}
