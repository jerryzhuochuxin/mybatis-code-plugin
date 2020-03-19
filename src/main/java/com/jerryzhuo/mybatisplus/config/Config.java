package com.jerryzhuo.mybatisplus.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.generator.config.*;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author zhuochuxin
 * @create 2020/1/30
 * 总体配置
 */
@Data
public class Config {
    private GDataSourceConfig gDataSourceConfig;
    private GGlobalConfig gGlobalConfig;
    private GPackageConfig gPackageConfig;
    private GStrategyConfig gStrategyConfig;
    private GTemplateConfig gTemplateConfig;

    public static Config getConfig(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);
        StringBuilder jsonBuilder = new StringBuilder();
        while (in.hasNext()) {
            jsonBuilder.append(in.next());
        }
        return JSONObject.parseObject(jsonBuilder.toString(), Config.class);
    }

    @JSONField(serialize = false)
    public DataSourceConfig getDataSourceConfig() {
        return gDataSourceConfig.getDataSourceConfig();
    }

    @JSONField(serialize = false)
    public GlobalConfig getGlobalConfig() {
        return gGlobalConfig.getGlobalConfig();
    }

    @JSONField(serialize = false)
    public PackageConfig getPackageConfig() {
        return gPackageConfig.getPackageConfig();
    }

    @JSONField(serialize = false)
    public StrategyConfig getStrateyConfig() {
        return gStrategyConfig.getStrategyConfig();
    }

    @JSONField(serialize = false)
    public TemplateConfig getTemplateConfig() {
        return gTemplateConfig.getTemplateConfig();
    }
}
