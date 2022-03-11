package com.crystal.feature.controller;

import com.crystal.feature.model.dto.UserRegisterDto;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.RegisterService;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CHUNHAO LIU
 * 注册相关接口
 */
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 用户注册
     */
    @RequestMapping(value = "/userRegister")
    public ResultVo<String> userRegister(HttpServletRequest request, @RequestBody UserRegisterDto dto) {
        ResultVo<String> vo=new ResultVo<>();
        //注册接口
        try {
            vo=registerService.userRegister(request,dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

}
