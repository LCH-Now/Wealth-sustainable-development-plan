package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author CHUNHAO LIU
 * 快乐8购买详情
 */
@Data
public class HappyEightBuyDetailDto {

    /**
     * 快乐8购买主键ID
     */
    private String id;

    /**
     * 彩票期号
     */
    @NotNull(message = CommonMessageConstant.BATCH_NUMBER_IS_NULL)
    private String batchNumber;

    /**
     * 彩票玩法选一 选二 选三等
     */
    @NotNull(message = CommonMessageConstant.PLAY_TYPE_IS_NULL)
    private String playType;

    /**
     * 购买注数
     */
    @NotNull(message = CommonMessageConstant.QUANTITY_IS_NULL)
    private Integer quantity;

    /**
     * 选择号码
     */
    @NotNull(message = CommonMessageConstant.NUMBER_IS_NULL)
    private String[] numberArr;

}
