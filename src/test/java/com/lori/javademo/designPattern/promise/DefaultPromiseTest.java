package com.lori.javademo.designPattern.promise;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultPromiseTest {

    @Autowired
    private PayService payService;

    /**
     * 测试对付款返回的值进行操作
     * 再次操作会抛出不能修改数据的异常,从而保证结果的正确性
     */
    @Test
    public void testDefaultPromise() {
        DefaultPromise successPromise = payService.pay(10);
        Assert.assertTrue(successPromise.isSuccess());

        try {
            successPromise.setSuccess();
        }catch (RuntimeException e){
            e.printStackTrace();
            Assert.assertEquals(successPromise.getFAILMESSAGE(),e.getMessage());
        }

        DefaultPromise failPromise = payService.pay(-10);
        Assert.assertFalse(failPromise.isSuccess());

        try {
            failPromise.setFail();
        }catch (RuntimeException e){
            e.printStackTrace();
            Assert.assertEquals(failPromise.getFAILMESSAGE(),e.getMessage());
        }
    }
}
