package com.crystal.feature.model.entity;

import lombok.Data;

/**
 * @author CHUNHAO LIU
 * 角色菜单管理实体类
 */
@Data
public class RoleMenuEntity {


    /**
     * 主键Id
     */
    private String id;

    /**
     * 角色Id;
     */
    private String roleId;

    /**
     * 菜单编号
     */
    private String menuId;

}
