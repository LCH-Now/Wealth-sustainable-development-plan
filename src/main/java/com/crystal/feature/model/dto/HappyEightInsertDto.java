package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author CHUNHAO LIU
 * 快乐8新增实体类
 */
@Data
public class HappyEightInsertDto {

    /**
     * 开奖日期
     */
    @NotNull(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private Date lotteryDate;
    /**
     * 开奖日期
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String batchNumber;
    /**
     * 中奖号码1
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String one;
    /**
     * 中奖号码2
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String two;
    /**
     * 中奖号码3
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String three;
    /**
     * 中奖号码4
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String four;
    /**
     * 中奖号码5
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String five;
    /**
     * 中奖号码6
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String six;
    /**
     * 中奖号码7
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String seven;
    /**
     * 中奖号码8
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String eight;
    /**
     * 中奖号码9
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String nine;
    /**
     * 中奖号码10
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String ten;
    /**
     * 中奖号码11
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String eleven;
    /**
     * 中奖号码12
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String twelve;
    /**
     * 中奖号码13
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String thirteen;
    /**
     * 中奖号码14
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String fourteen;
    /**
     * 中奖号码15
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String fifteen;
    /**
     * 中奖号码16
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String sixteen;
    /**
     * 中奖号码17
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String seventeen;
    /**
     * 中奖号码18
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String eighteen;
    /**
     * 中奖号码19
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
    private String nineteen;
    /**
     * 中奖号码20
     */
    @NotEmpty(message = CommonMessageConstant.REQUIRED_ITEMS_ARE_NOT_FILLED_IN)
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
