package com.bc.data.service;

import com.bc.data.domain.InterfaceResPara;
import com.bc.util.db.IBaseService;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface IInterfaceResParaService extends IBaseService<InterfaceResPara> {
    /**
     * 获取接口对应的所有请求参数列表
     * @param res_list_id
     * @return
     */
    List<InterfaceResPara> getInterfaceResParaList(String res_list_id);
}
