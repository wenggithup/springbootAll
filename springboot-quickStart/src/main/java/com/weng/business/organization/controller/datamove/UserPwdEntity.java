package com.weng.business.organization.controller.datamove;

import lombok.Data;

/**
 * @DATE: 2022/8/11 5:36 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class UserPwdEntity {
    private Long userID;

    private Long SKDID;

    private String pwd;

    private Long lockDeadline;

    private int pwdStrength;

    private Long pwdUpdateTime;
}
