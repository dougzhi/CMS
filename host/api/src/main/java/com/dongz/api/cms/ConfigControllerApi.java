package com.dongz.api.cms;

import com.dongz.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author dong
 * @date 2020/4/3 16:25
 * @desc
 */
@Api(tags="cms配置管理接口")
public interface ConfigControllerApi {
    @ApiOperation("根据id查询CMS配置信息")
    CmsConfig getModel(String id);
}
