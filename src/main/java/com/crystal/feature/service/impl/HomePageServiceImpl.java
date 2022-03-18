package com.crystal.feature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crystal.feature.common.constant.CommonCodeConstant;
import com.crystal.feature.mapper.LotteryBuyDetailMapper;
import com.crystal.feature.model.entity.LotteryBuyDetailEntity;
import com.crystal.feature.model.vo.HomePageMainVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHUNHAO LIU
 * 彩票购买接口实现类
 */
@Service
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    private LotteryBuyDetailMapper lotteryBuyDetailMapper;

    @Override
    public ResultVo<HomePageMainVo> baseInfo(String lotteryType) {
        QueryWrapper<LotteryBuyDetailEntity> queryWrapper = new QueryWrapper<LotteryBuyDetailEntity>().eq("open", CommonCodeConstant.IS_OPEN_LOTTERY_TIME);
        if (null != lotteryType && !"".equals(lotteryType)) {
            //如果类型字段不为空的话,查询固定彩票的种类
            queryWrapper.eq("lottery_type", lotteryType);
        }
        //依据彩票类型查询购买数据
        List<LotteryBuyDetailEntity> lotteryBuyDetailEntityList = lotteryBuyDetailMapper.selectList(queryWrapper);
        if (null == lotteryBuyDetailEntityList || lotteryBuyDetailEntityList.size() == 0) {

            return new ResultVo<HomePageMainVo>().sucess(new HomePageMainVo());
        }
        //定义初始变量中奖中奖总金额
        Float totalBonus = 0.00f;
        //总成本
        Float totalPurchaseAmount = 0.00f;
        //总利润
        Float totalNetProfit = 0.00f;
        //中奖率
        Integer winningRate = 0;
        //最大中奖金额
        Float maxBonus = 0.00f;
        //平均奖金
        Float avgBonus = 0.00f;
        //购买数量
        Integer quantity = 0;

        for (int i = 0; i < lotteryBuyDetailEntityList.size(); i++) {
            LotteryBuyDetailEntity lotteryBuyDetailEntity = lotteryBuyDetailEntityList.get(i);
            //当前奖金
            Float currentBonus=lotteryBuyDetailEntity.getBonus();
            totalBonus +=currentBonus;
            totalPurchaseAmount+=lotteryBuyDetailEntity.getPurchaseAmount();
            totalNetProfit+=lotteryBuyDetailEntity.getNetProfit();
            if(maxBonus<currentBonus){
                maxBonus=currentBonus;
            }
            quantity+=lotteryBuyDetailEntity.getQuantity();
        }
        return null;
    }
}
