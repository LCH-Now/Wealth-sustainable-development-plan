package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author CHUNHAO LIU
 * 快乐8新增实体类
 */
@Data
public class HappyEightInsertDto {

    /**
     * 开奖日期
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String lotteryDate;

    /**
     * 中奖号码1
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String one;
    /**
     * 中奖号码2
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String two;
    /**
     * 中奖号码3
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String three;
    /**
     * 中奖号码4
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String four;
    /**
     * 中奖号码5
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String five;
    /**
     * 中奖号码6
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String six;
    /**
     * 中奖号码7
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String seven;
    /**
     * 中奖号码8
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String eight;
    /**
     * 中奖号码9
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String nine;
    /**
     * 中奖号码10
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String ten;
    /**
     * 中奖号码11
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String eleven;
    /**
     * 中奖号码12
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String twelve;
    /**
     * 中奖号码13
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String thirteen;
    /**
     * 中奖号码14
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String fourteen;
    /**
     * 中奖号码15
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String fifteen;
    /**
     * 中奖号码16
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String sixteen;
    /**
     * 中奖号码17
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String seventeen;
    /**
     * 中奖号码18
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String eighteen;
    /**
     * 中奖号码19
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String nineteen;
    /**
     * 中奖号码20
     */
    @NotBlank(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String twenty;

    @Override
    public String toString() {
        return
                one + ',' + two + ','
                        + three + ','
                        + four + ','
                        + five + ','
                        + six + ','
                        + seven + ','
                        + eight + ','
                        + nine + ','
                        + ten + ','
                        + eleven + ','
                        + twelve + ',' + thirteen + ','
                        + fourteen + ','
                        + fifteen + ','
                        + sixteen + ','
                        + seventeen + ','
                        + eighteen + ','
                        + nineteen + ','
                        + twenty + ',';
    }
}
