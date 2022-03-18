package com.crystal.feature.model.vo;

import lombok.Data;

/**
 * @author CHUNHAO LIU
 * 首页数据展示
 */
@Data
public class HomePageMainVo {

    /**
     * 中奖总金额
     */
    private Float totalBonus=0.00f;

    /**
     * 总成本
     */
    private Float totalPurchaseAmount=0.00f;

    /**
     * 总利润
     */
    private Float totalNetProfit=0.00f;

    /**
     * 中奖率
     */
    private String winningRate="0%";

    /**
     * 最大中奖金额
     */
    private Float maxBonus=0.00f;

    /**
     * 平均每注中奖金额
     */
    private Float avgBonus=0.00f;


}
