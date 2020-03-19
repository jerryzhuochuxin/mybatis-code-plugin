package com.jerryzhuo.mybatisplus.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GStrategyConfig {
    private boolean isCapitalMode = false;
    private boolean skipView = false;
    private String[] tablePrefix;
    private String[] fieldPrefix;
    private String superEntityClass;
    private String[] superEntityColumns;
    private String superMapperClass;
    private String superServiceClass;
    private String superServiceImplClass;
    private String superControllerClass;
    private String[] include;
    private String[] exclude;
    private boolean entitySerialVersionUID;
    private boolean entityColumnConstant;
    private boolean entityBuilderModel;
    private boolean entityLombokModel;
    private boolean entityBooleanColumnRemoveIsPrefix;
    private boolean restControllerStyle;
    private boolean controllerMappingHyphenStyle;
    private boolean entityTableFieldAnnotationEnable;
    private String versionFieldName;
    private String logicDeleteFieldName;

    @JSONField(serialize = false)
    public StrategyConfig getStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(isCapitalMode);
        strategyConfig.setSkipView(skipView);
        strategyConfig.setTablePrefix(tablePrefix);
        strategyConfig.setFieldPrefix(fieldPrefix);
        strategyConfig.setSuperEntityClass(superEntityClass);
        strategyConfig.setSuperEntityColumns(superEntityColumns);
        strategyConfig.setSuperMapperClass(superMapperClass);
        strategyConfig.setSuperServiceClass(superServiceClass);
        strategyConfig.setSuperServiceImplClass(superServiceImplClass);
        strategyConfig.setInclude(include);
        strategyConfig.setExclude(exclude);
        strategyConfig.setEntitySerialVersionUID(entitySerialVersionUID);
        strategyConfig.setEntityColumnConstant(entityColumnConstant);
        strategyConfig.setEntityBuilderModel(entityBuilderModel);
        strategyConfig.setEntityLombokModel(entityLombokModel);
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(entityBooleanColumnRemoveIsPrefix);
        strategyConfig.setRestControllerStyle(restControllerStyle);
        strategyConfig.setControllerMappingHyphenStyle(controllerMappingHyphenStyle);
        strategyConfig.setEntityTableFieldAnnotationEnable(entityTableFieldAnnotationEnable);
        strategyConfig.setVersionFieldName(versionFieldName);
        strategyConfig.setLogicDeleteFieldName(logicDeleteFieldName);

        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        return strategyConfig;
    }

    public static GStrategyConfig createObject(boolean entityLombokModel, boolean restControllerStyle, boolean entityTableFieldAnnotationEnable, List<String> include) {
        GStrategyConfig result = new GStrategyConfig();
        result.setEntityLombokModel(entityLombokModel);
        result.setRestControllerStyle(restControllerStyle);
        result.setEntityTableFieldAnnotationEnable(entityTableFieldAnnotationEnable);

        if (include == null || include.size() == 0) {
            //防止全库生成
            include.add(UUID.randomUUID().toString());
        }

        String[] includes = new String[include.size()];
        for (int i = 0; i < include.size(); i++) {
            includes[i] = include.get(i);
        }
        result.setInclude(includes);
        return result;
    }
}
