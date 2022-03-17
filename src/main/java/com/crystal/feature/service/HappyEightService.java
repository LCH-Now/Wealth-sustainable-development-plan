package com.crystal.feature.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crystal.feature.model.dto.HappyEightBuyDetailDto;
import com.crystal.feature.model.dto.HappyEightInsertDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.HappyEightBuyDetailEntity;
import com.crystal.feature.model.vo.*;

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


    /**
     * 保存彩票购买信息
     *
     * @param dto 彩票购买信息
     * @return
     */
    ResultVo<String> saveBuyDetail(HappyEightBuyDetailDto dto);


    /**
     * 查询快乐8彩票列表展示数据
     * @param dto 分页信息
     * @return
     */
    ResultVo<IPage<HappyEightBuyDetailEntity>> queryBuyDetailList(PageDto dto);

    /**
     * 查询快乐8彩票列表展示数据
     * @param id 彩票购买详情编号
     * @return
     */
    ResultVo<HappyEightBuyDetailEntity> queryLotteryInfo(String id);

}
