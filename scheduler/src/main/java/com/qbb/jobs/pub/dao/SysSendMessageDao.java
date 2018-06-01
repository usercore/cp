package com.qbb.jobs.pub.dao;

import com.qbb.jobs.pub.model.SysSendMessage;


public interface SysSendMessageDao {

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(SysSendMessage record);

    /**
     *
     * @param record
     */
    int insertSelective(SysSendMessage record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param sendUuid
     */
    SysSendMessage selectByPrimaryKey(String sendUuid);

}