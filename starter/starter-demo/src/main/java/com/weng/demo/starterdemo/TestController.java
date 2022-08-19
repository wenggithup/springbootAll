package com.weng.demo.starterdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DATE: 2022/6/7 12:43 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String getTest(){
        return "success";
    }
}
