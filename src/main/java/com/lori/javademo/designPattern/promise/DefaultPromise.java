package com.lori.javademo.designPattern.promise;

/**
 * promise目前在JavaScript流行,为解决多层嵌套异步调用的问题
 * netty使用的promise是为了保证数据未被修改过
 */
public class DefaultPromise {

    /**
     * 标识,初始值为null,只能赋值一次,如果被第二次赋值,将会抛出异常
     * 从而保证数据的正确性
     */
    private Boolean successFlag = null;

    private final Object object = new Object();

    private final String FAILMESSAGE = "can not change data";

    public void setSuccess(){
        synchronized (object){
            if (null != successFlag){
                throw new RuntimeException(FAILMESSAGE);
            }
            this.successFlag = true;
        }
    }

    public void setFail(){
        synchronized (object){
            if (null != successFlag){
                throw new RuntimeException(FAILMESSAGE);
            }
            this.successFlag = false;
        }
    }

    public boolean isSuccess(){
        return successFlag;
    }

    public String getFAILMESSAGE(){
        return FAILMESSAGE;
    }
}
