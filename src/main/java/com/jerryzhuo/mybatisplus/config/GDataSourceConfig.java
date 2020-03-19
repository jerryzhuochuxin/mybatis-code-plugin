package com.jerryzhuo.mybatisplus.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import lombok.Data;

@Data
public class GDataSourceConfig {
    private String schemaName;
    private String url;
    private String driverName;
    private String username;
    private String password;

    @JSONField(serialize = false)
    public DataSourceConfig getDataSourceConfig() {
        DataSourceConfig result = new DataSourceConfig();
        result.setSchemaName(schemaName);
        result.setUrl(url);
        result.setDriverName(driverName);
        result.setUsername(username);
        result.setPassword(password);
        return result;
    }

    public static GDataSourceConfig createObject(String url, String username, String password, String driverName) {
        GDataSourceConfig result = new GDataSourceConfig();
        result.setUrl(url);
        result.setUsername(username);
        result.setPassword(password);
        result.setDriverName(driverName);
        return result;
    }
}
