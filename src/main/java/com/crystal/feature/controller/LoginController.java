package com.crystal.feature.controller;

import com.crystal.feature.model.dto.LoginDto;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.model.vo.UserVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHUNHAO LIU
 * 登入相关接口
 */
@RestController
@RequestMapping(value = "/userLogin")
public class LoginController {


    /**
     * 登录方法
     */
    @RequestMapping(value = "/login")
    public ResultVo<UserVo> login(@RequestBody LoginDto dto) {
        ResultVo<UserVo> vo=new ResultVo<>();
        return  vo.sucess();
    }
}
