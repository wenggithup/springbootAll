package com.weng.demo.business.service.impl;

import com.weng.demo.business.entity.TOrder;
import com.weng.demo.business.mapper.TOrderMapper;
import com.weng.demo.business.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类 <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-08
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

}
