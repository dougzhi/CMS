package com.dongz.cms.controller.web;

import com.dongz.api.cms.ConfigControllerApi;
import com.dongz.cms.service.ConfigService;
import com.dongz.framework.domain.cms.CmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dong
 * @date 2020/4/3 16:35
 * @desc
 */
@RestController
@RequestMapping("/cms/config")
public class ConfigController implements ConfigControllerApi {

    @Autowired
    ConfigService configService;

    @Override
    @GetMapping("/getModel/{id}")
    public CmsConfig getModel(@PathVariable("id") String id) {
        return configService.getConfigById(id);
    }
}
