package com.dongz.framework.exception;

import com.dongz.framework.model.response.ResultCode;

/**
 * @author dong
 * @date 2020/4/2 23:40
 * @desc
 */
public class ExceptionCast {
    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
