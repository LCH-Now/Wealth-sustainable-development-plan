package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author CHUNHAO LIU
 * 用户注册实体类
 */
@Data
public class UserRegisterDto {
    /**
     * 姓名
     */
    @NotBlank(message = CommonMessageConstant.NAME_IS_NULL)
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = CommonMessageConstant.PASSWORD_IS_NULL)
    private String password;

    /**
     * 性别
     */
    @NotBlank(message = CommonMessageConstant.SEX_IS_NULL)
    private String sex;

    /**
     * 身份证号
     */
    @NotBlank(message = CommonMessageConstant.ID_CARD_IS_NULL)
    private String idCard;

    /**
     * 手机号码
     */
    @NotBlank(message = CommonMessageConstant.PHONE_IS_NULL)
    private String phone;
}
