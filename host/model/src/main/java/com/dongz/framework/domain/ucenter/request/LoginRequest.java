package com.dongz.framework.domain.ucenter.request;

import com.dongz.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
