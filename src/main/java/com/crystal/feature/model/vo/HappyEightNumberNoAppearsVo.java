package com.crystal.feature.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author CHUNHAO LIU
 * 未出现的号码的集合
 */
@Data
public class HappyEightNumberNoAppearsVo {

    /**
     * 期数
     */
    int stage;

    /**
     * 号码集合
     */
    List<String> numberList;

    public HappyEightNumberNoAppearsVo(int stage, List<String> numberList) {
    }
}
