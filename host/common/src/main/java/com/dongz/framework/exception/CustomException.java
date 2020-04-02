package com.dongz.framework.exception;

import com.dongz.framework.model.response.ResultCode;

/**
 * @author dong
 * @date 2020/4/2 23:39
 * @desc
 */
public class CustomException extends RuntimeException {
    private final ResultCode resultCode;
    public CustomException(ResultCode resultCode) { //异常信息为错误代码+异常信息
        super("错误代码:"+resultCode.code()+"错误信息:"+resultCode.message());
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode(){
        return this.resultCode;
    }
}