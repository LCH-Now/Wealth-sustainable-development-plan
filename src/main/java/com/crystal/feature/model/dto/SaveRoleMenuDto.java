package com.crystal.feature.model.dto;

import com.crystal.feature.common.constant.CommonMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author CHUNHAO LIU
 * 保存角色菜单权限
 */
@Data
public class SaveRoleMenuDto {

    /**
     * 角色编号
     */
    @NotNull(message = CommonMessageConstant.ROLE_ID_IS_NULL)
    private String roleId;

    /**
     * 菜单权限列表
     */
    private List<String> menuId;
}
