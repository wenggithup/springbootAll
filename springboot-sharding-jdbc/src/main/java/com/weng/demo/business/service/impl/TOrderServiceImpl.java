package com.weng.demo.business.service.impl;

import com.weng.demo.business.entity.TOrder;
import com.weng.demo.business.mapper.TOrderMapper;
import com.weng.demo.business.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 *  服务实现类 <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-08
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {
    @Autowired
    private TOrderMapper tOrderMapper;

    @Override
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOrder() {
        for (int i = 0; i < 10000; i++) {
            TOrder tOrder = new TOrder();
            tOrder.setUserId(i+213);
            tOrder.setAddressId(i+1231231);
            tOrder.setStatus("this is status");
            if (i==6000){
                throw new RuntimeException();
            }
            tOrderMapper.insertOrder(tOrder);
        }
    }
}
