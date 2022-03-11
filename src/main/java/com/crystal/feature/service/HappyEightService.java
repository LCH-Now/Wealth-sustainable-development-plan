package com.crystal.feature.service;

import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.ResultVo;

/**
 * @author CHUNHAO LIU
 * 快乐8服务实现类
 */
public interface HappyEightService {

    /**
     * 查询倒序几期内没有出现过的号码
     * @param stage 输入期数
     * @return
     */
    ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(int stage);

}
