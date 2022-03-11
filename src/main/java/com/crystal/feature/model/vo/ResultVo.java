package com.crystal.feature.model.vo;

import com.crystal.feature.common.constant.CommonReturnConstant;
import lombok.Data;

/**
 * @author CHUNHAO LIU
 * 封装出统一的返回参数
 */
@Data
public class ResultVo<T> {

    private String ret;
    private String msg;
    private T data;

    public ResultVo() {
    }

    public ResultVo(String ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public ResultVo(String ret, String msg, T data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo<T> sucess() {
        return new ResultVo<T>(CommonReturnConstant.SUCCESS_CODE,CommonReturnConstant.SUCCESS_MSG);
    }

    public ResultVo<T> sucess(T t) {
        return new ResultVo<T>(CommonReturnConstant.SUCCESS_CODE,CommonReturnConstant.SUCCESS_MSG,t);
    }

    public ResultVo<T> fail() {
        return new ResultVo<T>(CommonReturnConstant.FAIL_CODE,CommonReturnConstant.FAIL_MSG);
    }

    public ResultVo<T> fail(T t) {
        return new ResultVo<T>(CommonReturnConstant.FAIL_CODE,CommonReturnConstant.FAIL_MSG,t);
    }

}
