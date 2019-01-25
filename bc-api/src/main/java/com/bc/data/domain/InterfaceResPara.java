package com.bc.data.domain;

import com.bc.util.db.BasePage;

import java.io.Serializable;
import java.util.Date;

public class InterfaceResPara extends BasePage implements Serializable{
    private Integer id;

    private String resListId;

    private String paraName;

    private String paraDes;

    private String paraType;

    private String paraFormat;

    private String defaultValue;

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

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName == null ? null : paraName.trim();
    }

    public String getParaDes() {
        return paraDes;
    }

    public void setParaDes(String paraDes) {
        this.paraDes = paraDes == null ? null : paraDes.trim();
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType == null ? null : paraType.trim();
    }

    public String getParaFormat() {
        return paraFormat;
    }

    public void setParaFormat(String paraFormat) {
        this.paraFormat = paraFormat == null ? null : paraFormat.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InterfaceResPara{" +
                "id=" + id +
                ", resListId='" + resListId + '\'' +
                ", paraName='" + paraName + '\'' +
                ", paraDes='" + paraDes + '\'' +
                ", paraType='" + paraType + '\'' +
                ", paraFormat='" + paraFormat + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}