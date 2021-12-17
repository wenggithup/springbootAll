package com.weng.demo.business.service;

import com.weng.demo.business.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  服务类 <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-08
 */
public interface ITOrderService extends IService<TOrder> {

    void addOrder();
}
