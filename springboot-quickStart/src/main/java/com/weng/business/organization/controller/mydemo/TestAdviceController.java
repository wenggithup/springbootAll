package com.weng.business.organization.controller.mydemo;

import com.weng.business.organization.mapper.ImEnterpriseOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DATE: 2022/9/1 10:55 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestAdviceController {

    @Autowired
    ImEnterpriseOrganizationMapper organizationMapper;

    @GetMapping("/testAdvice")
    public void getTestAdvice(){
        Integer integer = organizationMapper.selectCount();
        System.out.println(integer);
    }
}
