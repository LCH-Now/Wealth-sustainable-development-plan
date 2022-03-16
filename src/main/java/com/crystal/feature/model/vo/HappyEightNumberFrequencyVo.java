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
    private String number;

    /**
     * 最近4场出现的次数
     */
    private Integer lastFour=0;

    /**
     * 最近4场出现的概率
     */
    private String lastFourFrequency="0";

    /**
     * 最近8场出现的次数
     */
    private Integer lastEight=0;

    /**
     * 最近8场出现的概率
     */
    private String lastEightFrequency="0";

    /**
     * 最近12场出现的次数
     */
    private Integer lastTwelve=0;

    /**
     * 最近12场出现的概率
     */
    private String lastTwelveFrequency="0";

    /**
     * 最近16场出现的次数
     */
    private Integer lastSixteen=0;

    /**
     * 最近16场出现的概率
     */
    private String lastSixteenFrequency="0";
    
    /**
     * 总次数
     */
    private Integer totalTime=0;

    /**
     * 总概率
     */
    private String totalTimeFrequency="0";

    public HappyEightNumberFrequencyVo(String number) {
        this.number = number;
    }

    public HappyEightNumberFrequencyVo(String number, Integer totalTime) {
        this.number = number;
        this.totalTime = totalTime;
    }
}
