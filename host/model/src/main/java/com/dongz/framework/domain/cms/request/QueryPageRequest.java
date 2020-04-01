package com.dongz.framework.domain.cms.request;

import com.dongz.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author dong
 * @date 2020/3/31 17:26
 * @desc
 */
@Data
public class QueryPageRequest extends RequestData {
    //站点id
    @ApiModelProperty("站点id") private String siteId;
    //页面ID
    @ApiModelProperty("页面ID") private String pageId;
    //页面名称
    @ApiModelProperty("页面名称") private String pageName;
    //页面别名
    @ApiModelProperty("页面别名") private String pageAliase;
    //模版id
    @ApiModelProperty("模版id") private String templateId;

}
