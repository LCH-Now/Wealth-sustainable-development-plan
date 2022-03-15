package com.crystal.feature.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crystal.feature.model.dto.HappyEightInsertDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.vo.HappyEightNumberFrequencyVo;
import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.HappyEightQueryVo;
import com.crystal.feature.model.vo.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * @author CHUNHAO LIU
 * 快乐8服务实现类
 */
public interface HappyEightService {


    /**
     * 查询历史中奖信息
     * @param dto 查询分页信息
     * @return
     */
    ResultVo<IPage<Map<String,HappyEightQueryVo>>> query(PageDto dto);

    /**
     * 保存中奖日期以及中奖号码
     *
     * @param dto 中奖日期以及号码
     * @return
     */
    ResultVo<String> save(HappyEightInsertDto dto);

    /**
     * 查询倒序几期内没有出现过的号码
     *
     * @param stage 输入期数
     * @return
     */
    ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(int stage);


    /**
     * 查询倒序几期各号码出现的次数
     *
     * @param time 输入具体的次数
     * @return
     */
    ResultVo<List<HappyEightNumberFrequencyVo>> queryNumberFrequency(String time);

}
