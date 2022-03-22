package com.crystal.feature.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author CHUNHAO LIU
 * 菜单查询输出类
 */
@Data
public class MenuQueryVo {

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

    /**
     * 菜单子集
     */

    private List<MenuQueryVo> childMenus;
}
