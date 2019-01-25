package com.bc.data.domain;

import com.bc.util.db.BasePage;

import java.io.Serializable;
import java.util.Date;

public class InterfaceResList extends BasePage implements Serializable {
    private Integer id;

    private String resListId;

    private String actinaId;

    private String listName;

    private String sqlStr;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResListId() {
        return resListId;
    }

    public void setResListId(String resListId) {
        this.resListId = resListId == null ? null : resListId.trim();
    }

    public String getActinaId() {
        return actinaId;
    }

    public void setActinaId(String actinaId) {
        this.actinaId = actinaId == null ? null : actinaId.trim();
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr == null ? null : sqlStr.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InterfaceResList{" +
                "id=" + id +
                ", resListId='" + resListId + '\'' +
                ", actinaId='" + actinaId + '\'' +
                ", listName='" + listName + '\'' +
                ", sqlStr='" + sqlStr + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}