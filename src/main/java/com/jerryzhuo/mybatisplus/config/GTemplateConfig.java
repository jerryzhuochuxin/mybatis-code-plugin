package com.jerryzhuo.mybatisplus.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import lombok.Data;

@Data
public class GTemplateConfig {
    private String entity = "/templates/entity.java";
    private String entityKt = "/templates/entity.kt";
    private String service = "/templates/service.java";
    private String serviceImpl = "/templates/serviceImpl.java";
    private String mapper = "/templates/mapper.java";
    private String xml = "/templates/mapper.xml";
    private String controller = "/templates/controller.java";

    @JSONField(serialize = false)
    public TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(entity);
        templateConfig.setEntityKt(entityKt);
        templateConfig.setService(service);
        templateConfig.setServiceImpl(serviceImpl);
        templateConfig.setMapper(mapper);
        templateConfig.setXml(xml);
        templateConfig.setController(controller);
        return templateConfig;
    }

    public static GTemplateConfig createObject(boolean requireXml, boolean requireMapper, boolean requireController, boolean requireEntity, boolean requireService, boolean requireServiceImpl) {
        GTemplateConfig result = new GTemplateConfig();
        if (!requireXml) {
            result.setXml(null);
        }
        if (!requireMapper) {
            result.setMapper(null);
        }
        if (!requireController) {
            result.setController(null);
        }
        if (!requireEntity) {
            result.setEntity(null);
        }
        if (!requireService) {
            result.setService(null);
        }
        if (!requireServiceImpl) {
            result.setServiceImpl(null);
        }
        return result;
    }
}
