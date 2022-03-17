package com.crystal.feature.common.config;

import com.crystal.feature.model.vo.ResultVo;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author CHUNHAO LIU
 *
 * 同一异常处理
 */
@ControllerAdvice
public class ExceptionHandlerConfig {

    /**
     * 处理POST请求之中,使用@Valid注解 校验实体参数失败后抛出的异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo<String> exceptionHandler(MethodArgumentNotValidException e)
    {
        return new ResultVo<String>().fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }




}
