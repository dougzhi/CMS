package com.dongz.framework.domain.order.response;

import com.dongz.framework.model.response.ResponseResult;
import com.dongz.framework.model.response.ResultCode;
import com.dongz.framework.domain.order.XcOrders;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/26.
 */
@Data
@ToString
public class CreateOrderResult extends ResponseResult {
    private XcOrders xcOrders;
    public CreateOrderResult(ResultCode resultCode, XcOrders xcOrders) {
        super(resultCode);
        this.xcOrders = xcOrders;
    }


}
