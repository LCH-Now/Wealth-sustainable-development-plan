package com.crystal.feature.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CHUNHAO LIU
 * 菜单权限实体类
 */
@Data
@TableName("t_sys_menu")
public class SysMenuEntity {

    /**
     * 菜单编号
     */
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级ID
     */
    private String pid;

    /**
     * 菜单描述
     */
    private String desc;

    /**
     * 菜单列表
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单顺序
     */
    private String sort;
}
