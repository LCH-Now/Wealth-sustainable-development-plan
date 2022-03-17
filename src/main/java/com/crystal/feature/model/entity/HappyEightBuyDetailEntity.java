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
@TableName("t_happy_eight_detail_list")
public class HappyEightBuyDetailEntity {

    /**
     * 主键id
     */
    private String id;

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
     * 购买金额
     */
    private Float purchaseAmount;

    /**
     * 中奖金额
     */
    private Float winningAmount;

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
