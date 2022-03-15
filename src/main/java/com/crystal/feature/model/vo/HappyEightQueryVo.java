package com.crystal.feature.model.vo;

import lombok.Data;


/**
 * @author CHUNHAO LIU
 * 快乐8新增实体类
 */
@Data
public class HappyEightQueryVo {

    /**
     * 中奖号码1
     */
    private String one;

    /**
     * 中奖号码2
     */
    private String two;

    /**
     * 中奖号码3
     */
    private String three;

    /**
     * 中奖号码4
     */
    private String four;

    /**
     * 中奖号码5
     */
    private String five;

    /**
     * 中奖号码6
     */
    private String six;

    /**
     * 中奖号码7
     */
    private String seven;

    /**
     * 中奖号码8
     */
    private String eight;

    /**
     * 中奖号码9
     */
    private String nine;

    /**
     * 中奖号码10
     */
    private String ten;

    /**
     * 中奖号码11
     */
    private String eleven;

    /**
     * 中奖号码12
     */
    private String twelve;

    /**
     * 中奖号码13
     */
    private String thirteen;

    /**
     * 中奖号码14
     */
    private String fourteen;

    /**
     * 中奖号码15
     */
    private String fifteen;

    /**
     * 中奖号码16
     */
    private String sixteen;

    /**
     * 中奖号码17
     */
    private String seventeen;

    /**
     * 中奖号码18
     */
    private String eighteen;

    /**
     * 中奖号码19
     */
    private String nineteen;

    /**
     * 中奖号码20
     */
    private String twenty;

    public HappyEightQueryVo fillAttribute(String str) {
        //对中奖号码进行拆分填充属性
        String[] strArr = str.split(",");

        this.one = strArr[0];
        this.two = strArr[1];
        this.three = strArr[2];
        this.four = strArr[3];
        this.five = strArr[4];
        this.six = strArr[5];
        this.seven = strArr[6];
        this.eight = strArr[7];
        this.nine = strArr[8];
        this.ten = strArr[9];
        this.eleven = strArr[10];
        this.twelve = strArr[11];
        this.three = strArr[12];
        this.fourteen = strArr[13];
        this.fifteen = strArr[14];
        this.sixteen = strArr[15];
        this.seventeen = strArr[16];
        this.eighteen = strArr[17];
        this.nineteen = strArr[18];
        this.twenty = strArr[19];

        return this;
    }

}
