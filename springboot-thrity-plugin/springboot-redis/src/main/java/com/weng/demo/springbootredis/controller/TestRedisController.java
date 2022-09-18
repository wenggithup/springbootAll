package com.weng.demo.springbootredis.controller;

import com.weng.demo.springbootredis.util.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DATE: 2022/8/4 1:34 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestRedisController {
    @Resource
    RedisUtil redisUtil;

    @GetMapping("/testRedisSet")
    public void testRedisSet() {
        redisUtil.set("name", "lisi");

        String result = redisUtil.get("name");
        System.out.println(result);
    }
}
