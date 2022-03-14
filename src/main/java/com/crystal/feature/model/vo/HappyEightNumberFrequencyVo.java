package com.crystal.feature.model.vo;

import lombok.Data;

/**
 * @author CHUNAHO LIU
 * 号码出现的概率
 */
@Data
public class HappyEightNumberFrequencyVo {

    /**
     * 号码
     */
    private String Number;

    /**
     * 最近4场出现的次数
     */
    private Integer lastFour;

    /**
     * 最近4场出现的概率
     */
    private String lastFourFrequency;

    /**
     * 最近8场出现的次数
     */
    private Integer lastEight;

    /**
     * 最近8场出现的概率
     */
    private String lastEightFrequency;

    /**
     * 最近12场出现的次数
     */
    private Integer lastTwelve;

    /**
     * 最近12场出现的概率
     */
    private String lastTwelveFrequency;

    /**
     * 最近16场出现的次数
     */
    private Integer lastSixteen;

    /**
     * 最近16场出现的概率
     */
    private String lastSixteenFrequency;
    
    /**
     * 总次数
     */
    private Integer totalTime;

    /**
     * 总概率
     */
    private String totalTimeFrequency;

    public HappyEightNumberFrequencyVo(String number) {
        Number = number;
    }
}
