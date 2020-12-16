package com.happylife.core.controller.redis;

import com.happylife.core.common.Response;
import com.happylife.core.config.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Before ruuning this demo to test redis,
 * ensure redis server on 192.168.0.200 is working. If not, run command "systemctl start redis" if redis is installed already
 */

@RestController
public class RedisTestController {

    @Autowired
    RedisUtils redisUtils;
    private static final Logger logger  = LoggerFactory.getLogger(RedisTestController.class);

    @GetMapping(value = "/redis/test/{name}")
    public ResponseEntity<Object> hello(@PathVariable(value = "name") String name){
        boolean hasKey = redisUtils.exists(name);
        String res = "";
        if(hasKey){
            Object value = redisUtils.get(name);
            res = value.toString();
            logger.info(String.format("Got value %s for key %s", res, name));
        }else{
            res = "juan";
            redisUtils.set(name, res, 10L, TimeUnit.SECONDS);
            logger.info(String.format("Set value %s for key %s", res, name));

        }
        Response response = Response.success(res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
