package com.weng.business.organization.controller.datamove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DATE: 2022/8/15 2:45 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class DataMoveController {
    @Autowired
    DataMoveUtil dataMoveUtil;

    @GetMapping("/getDataMove")
    public void getDataMove(){
        dataMoveUtil.insertTable();
    }

}
