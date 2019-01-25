package com.bc.data.service;
import com.bc.data.domain.InterfaceReqPara;
import com.bc.util.db.IBaseService;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface IInterfaceReqParaService extends IBaseService<InterfaceReqPara> {
    /**
     * 获取数据库中配置 的所有的返回参数
     * @param actinaId
     * @return
     */
    List<InterfaceReqPara> getInterfaceReqParaList(String actinaId);

}
