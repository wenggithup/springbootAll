package com.weng.demo.controller;

import com.weng.demo.business.entity.TOrder0;
import com.weng.demo.business.mapper.TOrder0Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DATE: 2021/12/7 4:15 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class ShardingJdbcController {
   @Autowired
    private TOrder0Mapper tOrder0Mapper;

    @GetMapping("/getShardingJdbcController")
    public String getShardingJdbcController(){
        for (int i = 0; i < 10; i++) {
            TOrder0 tOrder = new TOrder0();
            tOrder.setUserId(i+213);
            tOrder.setAddressId(i+1231231);
            tOrder.setStatus("this is status");

            tOrder0Mapper.insert(tOrder);
        }
        return "success";
    }
}
