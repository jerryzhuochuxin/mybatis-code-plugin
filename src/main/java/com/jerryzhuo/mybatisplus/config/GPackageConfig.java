package com.jerryzhuo.mybatisplus.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import lombok.Data;

import java.util.Map;

@Data
public class GPackageConfig {
    private String parent = "com.baomidou";
    private String moduleName = null;
    private String entity = "entity";
    private String service = "service";
    private String serviceImpl = "service.impl";
    private String mapper = "mapper";
    private String xml = "mapper.xml";
    private String controller = "controller";
    private Map<String, String> pathInfo;

    @JSONField(serialize = false)
    public PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(parent);
        packageConfig.setModuleName(moduleName);
        packageConfig.setEntity(entity);
        packageConfig.setService(service);
        packageConfig.setServiceImpl(serviceImpl);
        packageConfig.setMapper(mapper);
        packageConfig.setXml(xml);
        packageConfig.setController(controller);
        packageConfig.setPathInfo(pathInfo);
        return packageConfig;
    }

    public static GPackageConfig createObject(String parent) {
        GPackageConfig result = new GPackageConfig();
        result.setParent(parent);
        return result;
    }
}
