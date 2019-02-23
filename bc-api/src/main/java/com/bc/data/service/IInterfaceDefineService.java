package com.bc.data.service;

import com.bc.data.domain.InterfaceDefine;
import com.bc.util.db.IBaseService;
/**
 * Created by Administrator on 2017/7/20.
 */
public interface IInterfaceDefineService extends IBaseService<InterfaceDefine> {
    /**
     * 鑾峰彇璇锋眰璇锋眰鍦板潃瀵瑰簲鐨� 鎺ュ彛瀹炰綋绫�
     * @param actina
     * @return
     */
    InterfaceDefine getInterfaceDefine(String actina, String channel, String app_version);
}
