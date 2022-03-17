package com.crystal.feature.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author CHUNHAO LIU
 * 快乐8实体类
 */
@Data
@TableName("t_happy_eight")
public class HappyEightEntity {


    /**
     * 主键ID
     */
    private String id;

    /**
     * 开奖日期
     */
    private Date lotteryDate;

    /**
     * 彩票期号
     */
    private String batchNumber;

    /**
     * 中奖号码
     */
    private String number;
}
