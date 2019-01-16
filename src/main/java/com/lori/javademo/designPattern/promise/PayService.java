package com.lori.javademo.designPattern.promise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    private static Logger logger = LoggerFactory.getLogger(PayService.class);

    public DefaultPromise pay(Integer money){
        DefaultPromise promise = new DefaultPromise();
        if (money < 0){
            logger.info("pay fail, money can not less than zero "+money);
            promise.setFail();
            return promise;
        }
        logger.info("pay success "+money);
        promise.setSuccess();
        return promise;
    }
}
