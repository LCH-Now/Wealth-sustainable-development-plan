package com.crystal.feature.service;

import com.crystal.feature.model.vo.HomePageMainVo;
import com.crystal.feature.model.vo.ResultVo;

/**
 * @author CHUNHAO LIU
 * 首页列表展示接口接口
 */
public interface HomePageService {


    /**
     * 查询彩票的基本信息
     * @param lotteryType
     * @return
     */
     ResultVo<HomePageMainVo> baseInfo(String lotteryType);

}
