package com.weng.business.organization.controller.datamove;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @DATE: 2022/8/11 6:00 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class UserExtMapper implements RowMapper<UserExtEntity> {
    @Override
    public UserExtEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserExtEntity userExtEntity = new UserExtEntity();
        userExtEntity.setUserID(rs.getLong("userID"));
        userExtEntity.setSDKID(rs.getLong("SDKID"));
        userExtEntity.setColumn01(rs.getString("column01"));
        userExtEntity.setColumn02(rs.getString("column02"));
        userExtEntity.setColumn03(rs.getString("column03"));
        userExtEntity.setColumn04(rs.getString("column04"));
        userExtEntity.setColumn05(rs.getString("column05"));
        userExtEntity.setColumn06(rs.getString("column06"));
        userExtEntity.setColumn07(rs.getString("column07"));
        userExtEntity.setColumn08(rs.getString("column08"));
        userExtEntity.setColumn09(rs.getString("column09"));
        userExtEntity.setColumn10(rs.getString("column10"));
        userExtEntity.setColumn11(rs.getString("column11"));
        userExtEntity.setColumn12(rs.getString("column12"));
        userExtEntity.setColumn13(rs.getString("column13"));
        userExtEntity.setColumn14(rs.getString("column14"));
        userExtEntity.setColumn15(rs.getString("column15"));
        userExtEntity.setColumn16(rs.getString("column16"));
        userExtEntity.setColumn17(rs.getString("column17"));
        userExtEntity.setColumn18(rs.getString("column18"));
        userExtEntity.setColumn19(rs.getString("column19"));
        userExtEntity.setColumn20(rs.getString("column20"));
        userExtEntity.setColumn21(rs.getString("column21"));
        userExtEntity.setColumn22(rs.getString("column22"));
        userExtEntity.setColumn23(rs.getString("column23"));
        return userExtEntity;
    }
}
