package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author CHUNHAO LIU
 * 菜单新增实体类
 */
@Data
public class AddMenuDto {

    /**
     * 菜单编号
     */
    private String id;

    /**
     * 菜单名称
     */
    @NotBlank(message = CommonMessageConstant.MENU_NAME_IS_NULL)
    private String name;

    /**
     * 父级ID
     */
    private String pid;

    /**
     * 菜单描述
     */
    @NotBlank(message = CommonMessageConstant.MENU_DESC_IS_NULL)
    private String desc;

    /**
     * 菜单列表
     */
    @NotBlank(message = CommonMessageConstant.MENU_URL_IS_NULL)
    private String url;

}
