package com.crystal.feature.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author CHUNHAO LIU
 * 彩票购买详情
 */
@Data
@TableName("t_lottery_buy_details_list")
public class LotteryBuyDetailEntity {

    /**
     * 主键id
     */
    private String id;

    /**
     * 彩票类型
     */
    private String lotteryType;

    /**
     * 彩票期号
     */
    private String batchNumber;

    /**
     * 玩法类型
     */
    private String playType;

    /**
     * 彩票号码
     */
    private String number;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private Float price;


    /**
     * 奖金
     */
    private Float bonus;

    /**
     * 购买金额
     */
    private Float PurchaseAmount;

    /**
     * 净利润
     */
    private Float netProfit;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 开奖标记
     */
    private String open;

    /**
     * 中奖号码
     */
    private String winningNumber;

    /**
     * 是否中奖
     */
    private String isWinning;


}
