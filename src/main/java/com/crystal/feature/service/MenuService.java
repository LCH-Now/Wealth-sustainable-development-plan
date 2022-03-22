package com.crystal.feature.service;

import com.crystal.feature.model.dto.AddMenuDto;
import com.crystal.feature.model.dto.SaveRoleMenuDto;
import com.crystal.feature.model.vo.MenuQueryVo;
import com.skq.base.vo.ResultVo;

import java.util.List;

/**
 * @author CHUNHAO LIU
 * 菜单实现类
 */
public interface MenuService {

    /**
     * 查询菜单权限
     * @return
     */
    ResultVo<List<MenuQueryVo>> queryMenu();

    /**
     * 菜单保存接口
     * @param dto
     * @return
     */
    ResultVo<String> saveMenu(AddMenuDto dto);

    /**
     * 角色菜单配置接口
     * @param dto
     * @return
     */
    ResultVo<String> saveRoleMenu(SaveRoleMenuDto dto);


}
