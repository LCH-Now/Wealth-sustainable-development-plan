package com.crystal.feature.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crystal.feature.model.dto.HappyEightBuyDetailDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.LotteryBuyDetailEntity;
import com.crystal.feature.model.vo.ResultVo;

/**
 * @author CHUNHAO LIU
 * 用户彩票购买详情管理
 */

public interface UserLotteryDetailService {

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
    ResultVo<IPage<LotteryBuyDetailEntity>> queryBuyDetailList(PageDto dto);

    /**
     * 查询快乐8彩票列表展示数据
     * @param id 彩票购买详情编号
     * @return
     */
    ResultVo<LotteryBuyDetailEntity> queryLotteryInfo(String id);
}
