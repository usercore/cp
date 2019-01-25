package com.bc.data.domain;

import com.bc.util.db.BasePage;

import java.io.Serializable;
import java.util.Date;

public class InterfaceDefine extends BasePage implements Serializable {
    private Integer id;

    private String actinaId;

    private String des;

    private Byte isLogin;

    private String appVersion;

    private Date createTime;

    private String channel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActinaId() {
        return actinaId;
    }

    public void setActinaId(String actinaId) {
        this.actinaId = actinaId == null ? null : actinaId.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Byte getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Byte isLogin) {
        this.isLogin = isLogin;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    @Override
    public String toString() {
        return "InterfaceDefine{" +
                "id=" + id +
                ", actinaId='" + actinaId + '\'' +
                ", des='" + des + '\'' +
                ", isLogin=" + isLogin +
                ", appVersion='" + appVersion + '\'' +
                ", createTime=" + createTime +
                ", channel='" + channel + '\'' +
                '}';
    }
}