package com.weng.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.demo.business.entity.ImEnterpriseOrganization;
import com.weng.demo.business.entity.OrderTest;
import com.weng.demo.business.entity.TOrder;
import com.weng.demo.business.mapper.ImEnterpriseOrganizationMapper;
import com.weng.demo.business.mapper.OrderTestMapper;
import com.weng.demo.business.mapper.TOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @DATE: 2021/12/7 4:15 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class ShardingJdbcController {
   @Autowired
    private TOrderMapper tOrder0Mapper;

   @Autowired
   private ImEnterpriseOrganizationMapper imEnterpriseOrganizationMapper;

   @Autowired
   private OrderTestMapper orderMapper;

    @GetMapping("/getShardingJdbcController")
    public String getShardingJdbcController(){
        List<TOrder> tOrders = tOrder0Mapper.selectList(new QueryWrapper<>());
        return tOrders.toString();
    }

    @GetMapping("/insertShardingJdbcController")
    public String insertShardingJdbcController(){
        for (int i = 0; i < 10; i++) {
            TOrder tOrder = new TOrder();
            tOrder.setUserId(i+213);
            tOrder.setAddressId(i+1231231);
            tOrder.setStatus("this is status");

            tOrder0Mapper.insertOrder(tOrder);
        }
        return "success";
    }

    @GetMapping("/testDefaultShardingJdbcController")
    public String testDefaultShardingJdbcController(){
        for (int i = 0; i < 10; i++) {
            OrderTest tOrder = new OrderTest();
            //tOrder.setId(i+1234l);
            //tOrder.setId("234");
            tOrder.setRemark(1234);



            orderMapper.insert(tOrder);
        }
        return "success";
    }

    @GetMapping("/testGetDefaultShardingJdbcController")
    public String testGetDefaultShardingJdbcController(){
        List<ImEnterpriseOrganization> imEnterpriseOrganizations = imEnterpriseOrganizationMapper.selectList(new QueryWrapper<>());
        return imEnterpriseOrganizations.toString();
    }
}
