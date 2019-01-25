package com.bc.data.service;

import com.bc.data.domain.InterfaceResList;
import com.bc.util.db.IBaseService;
/**
 * Created by Administrator on 2017/7/20.
 */
public interface IInterfaceResListService extends IBaseService<InterfaceResList> {
    /**
     * 获取对应的sql语句
     * @param actinaId
     * @return
     */
    InterfaceResList getInterfaceResList(String actinaId);
}
