package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author SKQ
 */
@Data
public class LoginDto {

    @NotBlank(message = CommonMessageConstant.USER_NAME_IS_NULL)
    private String userName;

    @NotBlank(message = CommonMessageConstant.PASSWORD_IS_NULL)
    private String password;
}
