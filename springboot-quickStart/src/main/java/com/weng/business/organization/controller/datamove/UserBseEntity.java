package com.weng.business.organization.controller.datamove;

import lombok.Data;

/**
 * @DATE: 2022/8/11 4:04 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class UserBseEntity {
    private Long userID;

    private Long SKDID;

    private byte userType;

    private String name ;

    private int status;

    private String portraitURL;
}
