package com.crystal.feature.common.constant;

import org.springframework.stereotype.Component;

/**
 * @author CHUNHAO LIU
 * 提示信息类
 */

public interface CommonMessageConstant {

    /**
     * 异常信息提示
     */
    String USER_NAME_IS_NULL = "请输入您的用户名";
    String PASSWORD_IS_NULL = "请输入您的密码";
    String NAME_IS_NULL = "请输入您的姓名";
    String SEX_IS_NULL = "请输入您的性别";
    String ID_CARD_IS_NULL = "请输入身份证号码";
    String PHONE_IS_NULL = "请输入手机号码";
    String USER_ALREADY_EXIST = "该用户已存在";
    String REQUIRED_ITEMS_ARE_NOT_FILLED_IN = "必填项未填写";

    /**
     * 成功信息提示
     */
    String REGISTER_SUCCESS="注册成功";
    String SAVE_SUCCESS="保存成功";
}
