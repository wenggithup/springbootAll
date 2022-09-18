package com.example.demo.controller;

import com.weng.demo.datasource.annotation.DynamicSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DATE: 2022/9/14 5:02 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class DemoController {
    @GetMapping("/test")
    public void getTest(){
        System.out.println(123);
    }
}
