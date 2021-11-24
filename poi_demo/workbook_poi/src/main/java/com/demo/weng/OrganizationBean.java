package com.demo.weng;

import java.util.List;

/**
 * @DATE: 2021/11/19 11:29 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */

public class OrganizationBean {

    private long orgID;

    private long parentOrgID;

    private String name;

    private String orgCode;

    private List<Long> orgIds;

    public List<Long> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Long> orgIds) {
        this.orgIds = orgIds;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgID() {
        return orgID;
    }

    public void setOrgId(long orgID) {
        this.orgID = orgID;
    }

    public long getParentOrgID() {
        return parentOrgID;
    }

    public void setParentOrgID(long parentOrgID) {
        this.parentOrgID = parentOrgID;
    }

    public OrganizationBean(long orgID, long parentOrgID) {
        this.orgID = orgID;
        this.parentOrgID = parentOrgID;
    }


    @Override
    public String toString() {
        return "OrganizationBean{" +
                "orgID=" + orgID +
                ", parentOrgID=" + parentOrgID +
                ", name='" + name + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", orgIds=" + orgIds +
                '}';
    }
}
