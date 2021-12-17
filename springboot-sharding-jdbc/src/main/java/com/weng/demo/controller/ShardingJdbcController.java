package com.weng.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.demo.business.entity.ImEnterpriseOrganization;
import com.weng.demo.business.entity.OrderTest;
import com.weng.demo.business.entity.TOrder;
import com.weng.demo.business.entity.TOrder202101;
import com.weng.demo.business.mapper.ImEnterpriseOrganizationMapper;
import com.weng.demo.business.mapper.OrderTestMapper;
import com.weng.demo.business.mapper.TOrder202101Mapper;
import com.weng.demo.business.mapper.TOrderMapper;
import com.weng.demo.business.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
   private ITOrderService itOrderService;

   @Autowired
   private ImEnterpriseOrganizationMapper imEnterpriseOrganizationMapper;

   @Autowired
   private OrderTestMapper orderMapper;
   @Autowired
   private TOrder202101Mapper tOrder202101Mapper;

    @GetMapping("/getShardingJdbcController")
    public String getShardingJdbcController(){
        List<TOrder> tOrders = tOrder0Mapper.selectList(new QueryWrapper<>());
        return tOrders.toString();
    }

    @GetMapping("/insertShardingJdbcController")
    public String insertShardingJdbcController(){
        itOrderService.addOrder();
        return "success";
    }

    @GetMapping("/insertIntervalShardingJdbcController")
    public String insertIntervalShardingJdbcController() throws ParseException {
        for (int i = 0; i < 10; i++) {
            TOrder202101 tOrder = new TOrder202101();
            tOrder.setName("this is test name："+i);
            tOrder.setPrice(2*i);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tOrder.setOrderTime(format.parse("2021-09-21 18:00:00"));

            tOrder202101Mapper.insertTOrder(tOrder);
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
