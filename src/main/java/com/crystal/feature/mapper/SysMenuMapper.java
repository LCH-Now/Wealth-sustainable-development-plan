package com.crystal.feature.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crystal.feature.model.entity.SysMenuEntity;
import com.crystal.feature.model.vo.MenuQueryVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 菜单mapper
 *
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 查询所有菜单
     * @return
     */
    @Select("select id,name,pid,desc,url,icon,sort from t_sys_menu order by sort asc")
    List<MenuQueryVo> queryRootMenu();


}
