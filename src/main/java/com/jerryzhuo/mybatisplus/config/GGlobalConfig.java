package com.jerryzhuo.mybatisplus.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import lombok.Data;

@Data
public class GGlobalConfig {
    private String outputDir = "~/";
    private boolean fileOverride = false;
    private boolean open = true;
    private boolean enableCache = false;
    private String author;
    private boolean kotlin = false;
    private boolean swagger2 = false;
    private boolean activeRecord = false;
    private boolean baseResultMap = false;
    private boolean baseColumnList;
    private String entityName;
    private String mapperName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;

    @JSONField(serialize = false)
    public GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(outputDir);
        globalConfig.setFileOverride(fileOverride);
        globalConfig.setOpen(open);
        globalConfig.setEnableCache(enableCache);
        globalConfig.setAuthor(author);
        globalConfig.setKotlin(kotlin);
        globalConfig.setSwagger2(swagger2);
        globalConfig.setActiveRecord(activeRecord);
        globalConfig.setBaseResultMap(baseResultMap);
        globalConfig.setBaseColumnList(baseColumnList);
        globalConfig.setEntityName(entityName);
        globalConfig.setMapperName(mapperName);
        globalConfig.setXmlName(xmlName);
        globalConfig.setServiceName(serviceName);
        globalConfig.setControllerName(controllerName);
        return globalConfig;
    }

    public static GGlobalConfig createObject(String outputDir, String author,boolean swagger2) {
        GGlobalConfig result = new GGlobalConfig();
        result.setOpen(false);
        result.setOutputDir(outputDir);
        result.setAuthor(author);
        result.setSwagger2(swagger2);
        return result;
    }
}
