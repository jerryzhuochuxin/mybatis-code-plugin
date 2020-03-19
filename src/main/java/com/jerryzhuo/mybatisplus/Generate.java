package com.jerryzhuo.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jerryzhuo.mybatisplus.config.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;


/**
 * @author zhuochuxin
 * @create 2020/1/29
 * 生成器入口
 */
@Mojo(name = "execute")
public class Generate extends AbstractMojo {
    @Parameter
    private String fileName;

    @Parameter
    private String url;
    @Parameter
    private String username;
    @Parameter
    private String password;
    @Parameter
    private String driverName = "com.mysql.jdbc.Driver";

    @Parameter
    private String parent = "";

    @Parameter
    private String outputDir = "src/main/java";
    @Parameter
    private String author = "codeGenerator";
    @Parameter
    private boolean swagger2 = false;

    @Parameter
    private boolean entityLombokModel = false;
    @Parameter
    private boolean restControllerStyle = false;
    @Parameter
    private boolean entityTableFieldAnnotationEnable = false;
    @Parameter
    private List<String> include = new ArrayList<>();

    @Parameter
    private boolean requireService = false;
    @Parameter
    private boolean requireServiceImpl = false;
    @Parameter
    private boolean requireController = false;
    @Parameter
    private boolean requireXml = true;
    @Parameter
    private boolean requireMapper = true;
    @Parameter
    private boolean requireEntity = true;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            getLog().info("----------------------------开始生成文件---------------------------------");
            execute(fileName);
            getLog().info("----------------------------生成文件成功---------------------------------");
        } catch (IOException e) {
            getLog().error("----------------------------生成文件失败---------------------------------");
        }
    }

    public void execute(String configFile) throws FileNotFoundException {
        Config config;
        if (StringUtils.isNotBlank(configFile)) {
            getLog().info("---->自定义配置文件:" + configFile + "，其他配置字段将无效");
            InputStream fileInputStream = new FileInputStream(configFile);
            config = Config.getConfig(fileInputStream);
        } else {
            getLog().info("---->无自定义文件，其他配置字段生效");
            config = new Config();
            config.setGGlobalConfig(GGlobalConfig.createObject(outputDir, author, swagger2));
            config.setGDataSourceConfig(GDataSourceConfig.createObject(url, username, password, driverName));
            config.setGPackageConfig(GPackageConfig.createObject(parent));
            config.setGStrategyConfig(GStrategyConfig.createObject(entityLombokModel, restControllerStyle, entityTableFieldAnnotationEnable, include));
            config.setGTemplateConfig(GTemplateConfig.createObject(requireXml, requireMapper, requireController, requireEntity, requireService, requireServiceImpl));
        }

        execute(config);
    }

    public void execute(Config config) {

        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        autoGenerator.setGlobalConfig(config.getGlobalConfig());

        // 数据源配置
        autoGenerator.setDataSource(config.getDataSourceConfig());

        // 包配置
        autoGenerator.setPackageInfo(config.getPackageConfig());

        // 配置模板
        autoGenerator.setTemplate(config.getTemplateConfig());

        // 策略配置
        autoGenerator.setStrategy(config.getStrateyConfig());


        //执行
        autoGenerator.execute();

        getLog().info("---->生成的配置如下:");
        System.out.println(JSONObject.toJSONString(config, SerializerFeature.WriteMapNullValue));
    }
}
