package com.crystal.feature.service;

import com.crystal.feature.model.vo.HappyEightNumberFrequencyVo;
import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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


    /**
     * 查询倒序几期各号码出现的次数
     * @param time 输入具体的次数
     * @return
     */
    ResultVo<List<HappyEightNumberFrequencyVo>> queryNumberFrequency(String time);
}
