package com.crystal.feature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crystal.feature.common.constant.CommonMessageConstant;
import com.crystal.feature.mapper.RoleMenuMapper;
import com.crystal.feature.mapper.SysMenuMapper;
import com.crystal.feature.model.dto.AddMenuDto;
import com.crystal.feature.model.dto.SaveRoleMenuDto;
import com.crystal.feature.model.entity.RoleMenuEntity;
import com.crystal.feature.model.entity.SysMenuEntity;
import com.crystal.feature.model.vo.MenuQueryVo;
import com.crystal.feature.service.MenuService;
import com.skq.base.vo.ResultVo;
import com.skq.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHUNHAO LIU
 * 菜单权限管理实现类
 */
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public ResultVo<List<MenuQueryVo>> queryMenu() {

        //先查询缓存中的用户数据,如果是超级管理员的则,查询全部。否者就查固定的表
        

        // 查询所有根目录
        List<MenuQueryVo> rootMenu = sysMenuMapper.queryRootMenu();

        // 最后的结果
        List<MenuQueryVo> menuList = new ArrayList<MenuQueryVo>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentId
            if (StringUtils.isBlank(rootMenu.get(i).getPid())) {
                menuList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuQueryVo menu : menuList) {
            menu.setChildMenus(getChild(menu.getId(), rootMenu));
        }

        return new ResultVo<List<MenuQueryVo>>().sucess(menuList);
    }

    @Override
    public ResultVo<String> saveMenu(AddMenuDto dto) {

        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(dto, sysMenuEntity);
        //判断菜单是新增还是修改之前的。
        String id = sysMenuEntity.getId();
        if (null == id || "".equals(id)) {
            //表示新增
            sysMenuEntity.setId(UuidUtil.getUuid());
            sysMenuMapper.insert(sysMenuEntity);
        }
        //表示修改
        sysMenuMapper.updateById(sysMenuEntity);

        return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
    }

    @Override
    public ResultVo<String> saveRoleMenu(SaveRoleMenuDto dto) {
        String roleId = dto.getRoleId();
        //删除当前菜单角色菜单。
        roleMenuMapper.delete(new QueryWrapper<RoleMenuEntity>().eq("role_id", roleId));
        List<String> menuList = dto.getMenuId();
        RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
        roleMenuEntity.setRoleId(roleId);
        //保存此次菜单
        for (int i = 0; i < menuList.size(); i++) {
            roleMenuEntity.setRoleId(menuList.get(i));
            roleMenuMapper.insert(roleMenuEntity);
        }

        return new ResultVo<String>().sucess();
    }


    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<MenuQueryVo> getChild(String id, List<MenuQueryVo> rootMenu) {
        // 子菜单
        List<MenuQueryVo> childList = new ArrayList<>();
        for (MenuQueryVo menu : rootMenu) {
            String pid = menu.getPid();
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(pid)) {
                if (pid.equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍,没有url子菜单还有子菜单
        for (MenuQueryVo menu : childList) {
            if (StringUtils.isBlank(menu.getUrl())) {
                // 递归
                menu.setChildMenus(getChild(menu.getId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
