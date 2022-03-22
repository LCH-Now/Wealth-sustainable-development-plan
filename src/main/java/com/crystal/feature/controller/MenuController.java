package com.crystal.feature.controller;

import com.crystal.feature.model.dto.AddMenuDto;
import com.crystal.feature.model.vo.UserVo;
import com.skq.base.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHUNHAO LIU
 * 菜单控制层
 */
@RestController
public class MenuController {


    /**
     * 保存菜单方法
     */
    @RequestMapping(value = "/saveMenu")
    public ResultVo<String> saveMenu(@RequestBody AddMenuDto dto) {
        ResultVo<String> vo=new ResultVo<>();
        return  vo.sucess();
    }
}
