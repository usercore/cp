package com.bc.data.domain;

import com.bc.util.db.BasePage;

import java.io.Serializable;
import java.util.Date;

public class InterfaceReqPara extends BasePage implements Serializable {
    private Integer id;

    private String actinaId;

    private String paraName;

    private String paraDes;

    private String paraType;

    private Byte paraLength;

    private Byte isNull;

    private Byte isEnc;

    private Date createTime;

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

    public Byte getParaLength() {
        return paraLength;
    }

    public void setParaLength(Byte paraLength) {
        this.paraLength = paraLength;
    }

    public Byte getIsNull() {
        return isNull;
    }

    public void setIsNull(Byte isNull) {
        this.isNull = isNull;
    }

    public Byte getIsEnc() {
        return isEnc;
    }

    public void setIsEnc(Byte isEnc) {
        this.isEnc = isEnc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InterfaceReqPara{" +
                "id=" + id +
                ", actinaId='" + actinaId + '\'' +
                ", paraName='" + paraName + '\'' +
                ", paraDes='" + paraDes + '\'' +
                ", paraType='" + paraType + '\'' +
                ", paraLength=" + paraLength +
                ", isNull=" + isNull +
                ", isEnc=" + isEnc +
                ", createTime=" + createTime +
                '}';
    }
}