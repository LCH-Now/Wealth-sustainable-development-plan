package com.crystal.feature.model.vo;

import lombok.Data;

/**
 * @author CHUNAHO LIU
 * 彩票购买历史数据展示
 */
@Data
public class HappyEightQueryBuyDetailListVo {

    /**
     * 主键id
     */
    private String id;

    /**
     * 购买期号
     */
    private String batchNumber;


    /**
     * 彩票玩法
     */
    private String playType;


    /**
     * 购买金额
     */
    private String purchaseAmount;


    /**
     * 购买注数
     */
    private String quantity;


    /**
     * 中奖金额
     */
    private String winningAmount;


    /**
     * 纯利润
     */
    private String netProfit;


    /**
     * 是否开奖
     */
    private String open;
}
