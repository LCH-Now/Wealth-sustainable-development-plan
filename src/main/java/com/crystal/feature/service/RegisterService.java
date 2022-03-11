package com.crystal.feature.service;

import com.crystal.feature.model.dto.UserRegisterDto;
import com.crystal.feature.model.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CHUNHAO LIU
 * 注册相关接口类
 */
public interface RegisterService {


    /**
     * 用户注册
     * @param dto
     * @return 注册生成的token
     * @throws Exception
     */
    ResultVo<String> userRegister(HttpServletRequest request, UserRegisterDto dto) throws Exception;
}
