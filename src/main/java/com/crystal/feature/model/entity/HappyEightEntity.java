package com.crystal.feature.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CHUNHAO LIU
 * 双色球实体类
 */
@Data
@TableName("t_happy_eight")
public class HappyEightEntity {


    private String id;

    private String lotteryDate;

    private String number;
}
