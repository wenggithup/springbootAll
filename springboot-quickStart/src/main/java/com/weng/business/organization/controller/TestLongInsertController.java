package com.weng.business.organization.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.business.organization.entity.ImEnterpriseOrganization;
import com.weng.business.organization.entity.ImLockDevice;
import com.weng.business.organization.mapper.ImEnterpriseOrganizationMapper;
import com.weng.business.organization.mapper.ImLockDeviceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @DATE: 2021/11/21 6:09 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestLongInsertController {
    @Autowired
    private ImEnterpriseOrganizationMapper imEnterpriseOrganizationMapper;

    @Autowired
    private ImLockDeviceMapper imLockDeviceMapper;

    @GetMapping("/testLongInsert")
    public String getTest(){
        /*List<ImEnterpriseOrganization> list = new ArrayList<>();
        ImEnterpriseOrganization l1 = new ImEnterpriseOrganization("123",new ArrayList<Long>(Arrays.asList(123213l,2132131l,21321534l,54335454l,6565858l)),"1");
        for (int i = 0; i < 10; i++) {
            list.add(l1);
        }
        Map<String,List<ImEnterpriseOrganization>>  map = new  HashMap<>();

        map.put("orgs",list);
//        imEnterpriseOrganizationMapper.insetByMap(map);*/
        List<ImLockDevice> imLockDevices = imLockDeviceMapper.selectList(new QueryWrapper<>());
        return imLockDevices.toString();
    }


    public static void main(String[] args) {
        ArrayList<Long> list = new ArrayList<>();
        list.add(1l);
        list.add(2l);
        list.add(3l);
        String collect = list.stream().map(t -> String.valueOf(t)).collect(Collectors.joining("-"));
        System.out.println(collect);

//        BeanUtils.copyProperties();

        String newStr = new String("this is un safe");
        String oldStr = new String("this is safe");
        String s = newStr.replaceFirst(newStr, oldStr);
        System.out.println(s);

    }
}
