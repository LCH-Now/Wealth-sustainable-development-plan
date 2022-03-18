package com.crystal.feature.common.constant;

/**
 * @author CHUNHAO LIU
 * 存放字符编码的位置
 */
public interface CommonCodeConstant {


    /**
     * 彩票类型编码快乐8-00
     */
    String LOTTERY_TYPE_HAPPY_EIGHT="00";

    /**
     * 快乐8编码
     */
    String HAPPY_EIGHT_LAST_FOUR = "4";
    String HAPPY_EIGHT_LAST_EIGHT = "8";
    String HAPPY_EIGHT_LAST_TWELVE = "12";
    String HAPPY_EIGHT_LAST_SIXTEEN = "16";

    /**
     * 快乐8玩法类型 从 选一 到 选十
     */
    String HAPPY_EIGHT_CHOOSE_ONE = "01";
    String HAPPY_EIGHT_CHOOSE_TWO = "02";
    String HAPPY_EIGHT_CHOOSE_THREE = "03";
    String HAPPY_EIGHT_CHOOSE_FOUR = "04";
    String HAPPY_EIGHT_CHOOSE_FIVE = "05";
    String HAPPY_EIGHT_CHOOSE_SIX = "06";
    String HAPPY_EIGHT_CHOOSE_SEVEN = "07";
    String HAPPY_EIGHT_CHOOSE_EIGHT = "08";
    String HAPPY_EIGHT_CHOOSE_NINE = "09";
    String HAPPY_EIGHT_CHOOSE_TEN = "10";

    /**
     * 是否开奖00-未开奖 01已开奖
     */
    String IS_NOT_OPEN_LOTTERY_TIME="00";
    String IS_OPEN_LOTTERY_TIME="01";

    /**
     * 是否中奖00-未中奖 01已中奖
     */
    String IS_NOT_WINNING="00";
    String IS_WINNING="01";
}
