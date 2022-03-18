package com.crystal.feature.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author CHUNHAO LIU
 * 快乐8彩票游戏规则
 */
@Data
@TableName("t_lottery_rules")
public class LotteryRulesEntity {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 彩票类型
     */
    private String lotteryType;
    /**
     * 玩法类型
     */
    private String playType;
    /**
     * 中奖个数
     */
    private Integer numberOfWinners;
    /**
     * 成本
     */
    private Float price;
    /**
     * 奖金
     */
    private Float bonus;
    /**
     * 纯利润
     */
    private Float netProfit;
    /**
     * 奖金比值
     */
    private String bonusRatio;
    /**
     * 更新时间
     */
    private Date updateTime;
}
