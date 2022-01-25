package com.weng.business.organization.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.protobuf.ByteString;
import com.ibm.etcd.api.*;
import com.ibm.etcd.client.ListenerObserver;
import com.ibm.etcd.client.kv.KvClient;
import com.ibm.etcd.client.kv.WatchUpdate;
import com.weng.business.organization.config.Test;
import com.weng.business.organization.entity.ImEnterpriseOrganization;
import com.weng.business.organization.entity.ImLockDevice;
import com.weng.business.organization.mapper.ImEnterpriseOrganizationMapper;
import com.weng.business.organization.mapper.ImLockDeviceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
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

    private KvClient kvClient;

    public TestLongInsertController(){
        System.out.println("无参构造器执行");
        kvClient= Test.getKvClient();
    }



    @GetMapping("/testEtcd")
    public String getEtcd(){
        String keyPrefix = "vrv-im-service-badwordServer-1.0";
        ByteString keyPrefixStr = ByteString.copyFrom(keyPrefix.getBytes(StandardCharsets.UTF_8));

        ByteString key1 = ByteString.copyFrom("vrv-im-service-badwordServer-1.0-127.0.0.1:11040:BF780C302C2F47B18OCE3F76F21E5D8D".getBytes(StandardCharsets.UTF_8));
        ByteString key2 = ByteString.copyFrom("vrv-im-service-badwordServer-1.0-127.0.0.1:11041:BF780C302C2F47B18OCE3F76F21E5D8D".getBytes(StandardCharsets.UTF_8));
        ByteString key3 = ByteString.copyFrom("vrv-im-config-key3".getBytes(StandardCharsets.UTF_8));
        ByteString key4 = ByteString.copyFrom("vrv-im-config-key4".getBytes(StandardCharsets.UTF_8));
        ByteString key5 = ByteString.copyFrom("vrv-im-config-key5".getBytes(StandardCharsets.UTF_8));
        ByteString value = ByteString.copyFrom("{\"codecEnabled\":false,\"key\":\"standardtest\",\"ip\":\"127.0.0.1\",\"port\":11306,\"username\":\"standard\",\"password\":\"6lrkJuArkWyaJPRq\",\"database\":\"IM_DBCONFIG\",\"databaseId\":\"mysql\"}".getBytes(StandardCharsets.UTF_8));

        /*PutRequest request = PutRequest.newBuilder().build();
        request.toBuilder().setKey(key1).setValue(value).setValue(value);*/
        kvClient.put(key1,value).sync();
        kvClient.put(key2,value).sync();
      /*  kvClient.put(key2,value).sync();
        kvClient.put(key3,value).sync();
        kvClient.put(key4,value).sync();*/
        RangeResponse rangeResponse = kvClient.get(keyPrefixStr).asPrefix().sync();
        System.out.println(rangeResponse.getCount());

/*
        kvClient.watch(keyPrefixStr).asPrefix().start(new ListenerObserver<WatchUpdate>() {
            @Override
            public void event(boolean b, WatchUpdate watchUpdate, Throwable throwable) {
                System.out.println(b);
                List<Event> events = watchUpdate.getEvents();
                System.out.println(events.toString());

                for (Event event : events) {
                    Event.EventType type = event.getType();
                    System.out.println(type.getNumber());
                    KeyValue kv = event.getKv();
                    ByteString key = kv.getKey();
                    ByteString value1 = kv.getValue();

                    System.out.println("key :"+key.toString(StandardCharsets.UTF_8)+",value :"+value1.toString(StandardCharsets.UTF_8));

                }

            }
        });
*/


        //rangeResponse.getKvs(rangeResponse.getCount())
        //System.out.println(rangeResponse.toString());

        //KeyValue keyValue = rangeResponse.getKvs(0);
        //String s = keyValue.getValue().toString(StandardCharsets.UTF_8);
        //System.out.println(s);
        //System.out.println(keyValue.getKey().toString());
        //System.out.println(keyValue.getValue().toString());

        //System.out.println(rangeResponse.toString());
       // System.out.println(sync.toString());
        return "success";
    }


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
 /*       ArrayList<Long> list = new ArrayList<>();
        list.add(1l);
        list.add(2l);
        list.add(3l);
        String collect = list.stream().map(t -> String.valueOf(t)).collect(Collectors.joining("-"));
        System.out.println(collect);

//        BeanUtils.copyProperties();

        String newStr = new String("this is un safe");
        String oldStr = new String("this is safe");
        String s = newStr.replaceFirst(newStr, oldStr);
        System.out.println(s);*/
        System.out.println("1".getBytes().length);

    }
}
