package com.crystal.feature.common.constant;

/**
 * @author CHUNHAO LIU
 * 提示信息类
 */

public interface CommonMessageConstant {

    /**
     * 必填异常信息提示
     */
    String USER_NAME_IS_NULL = "请输入您的用户名";
    String PASSWORD_IS_NULL = "请输入您的密码";
    String NAME_IS_NULL = "请输入您的姓名";
    String SEX_IS_NULL = "请输入您的性别";
    String ID_CARD_IS_NULL = "请输入身份证号码";
    String PHONE_IS_NULL = "请输入手机号码";
    String USER_ALREADY_EXIST = "该用户已存在";
    String REQUIRED_ITEMS_ARE_NOT_FILLED_IN = "必填项未填写";
    String LOTTERY_TYPE_IS_NULL = "请选择彩票类型";
    String BATCH_NUMBER_IS_NULL = "请输入彩票期号";
    String PLAY_TYPE_IS_NULL = "请选择彩票玩法";
    String QUANTITY_IS_NULL = "请输入购买数量";
    String NUMBER_IS_NULL = "请输入选择的号码";

    /**
     * 系统菜单信息提示语
     */
    String MENU_NAME_IS_NULL = "请输入菜单名称";
    String MENU_URL_IS_NULL = "请输入菜单地址";
    String MENU_DESC_IS_NULL = "请输入菜单描述";
    String ROLE_ID_IS_NULL = "请输入角色信息";


    /**
     * 查询为空信息异常
     */
    String LOTTERY_RULES_TO_BE_MAINTAIN="查无规则,请联系管理员维护";

    /**
     * 成功信息提示
     */
    String REGISTER_SUCCESS="注册成功";
    String SAVE_SUCCESS="保存成功";
}
