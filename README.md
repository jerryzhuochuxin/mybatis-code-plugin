# mybatis-plus代码生成优化

## 大多数项目是使用MyBatis Generator生成的代码，针对这个代码生成做了如下几个增强

1. 生成的实体可以带上lombok注解，数据库表中原生的注释，可以带上jpa注解，swagger2注解
根据配置可以生成controller，restController
2. 生成mapper，这个mapper默认继承mybatis-plus的接口，或者可以自定义继承
3. 生成service，根据配置可以自定义继承
4. 生成serviceImpl,默认实现service，根据配置可以自定义继承
还有很多其他的功能，只要是mybatis-plus-generate有的功能，基本都有
以上增强都可以手动设置，自由选择，而且生成的文件位置可以自由控制，对代码的入侵性较小，唯一一个麻烦的地方是，除非手动配置mapper的自定义继承类，不然会默认使用mybatis-plus的接口

### 使用方法：maven-plugin

#### 简单的说明：这个代码生成原本打算制作成idea plugin,然而经过研究，idea plugin的文件生成要经过类似这样的PsiFile这样的沙盒进行操作，制作成本很大，可能需要阅读源码改造整个mybatis-plus,因此这能放在maven plugin 中了，也带来一个较小的麻烦，需要配置信息

#### Maven plugin 配置如下: 大家可以将jar包安装在本地的maven仓库(后面说明怎么配置),有兴趣的同学可以把它制成starter,这样代码入侵性会小些

``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <pluginRepositories>
        <pluginRepository>
            <url>http://${username}:${password}@${ip}:${port}/repository/${repository}/</url>
            <id>3rd_repository</id>
        </pluginRepository>
    </pluginRepositories>
    <build>
        <plugins>
            <plugin>
                <groupId>com.jerryzhuo.mybatisPlus</groupId>
                <artifactId>generate</artifactId>
                <version>3.1.2</version>
                <dependencies>
                    <dependency>
                        <groupId>com.baomidou</groupId>
                        <artifactId>mybatis-plus-generator</artifactId>
                        <version>3.3.0</version>
                    </dependency>
                    <dependency>
                        <groupId>org.apache.velocity</groupId>
                        <artifactId>velocity-engine-core</artifactId>
                        <version>2.1</version>
                    </dependency>
                    <dependency>
                        <groupId>com.alibaba</groupId>
                        <artifactId>fastjson</artifactId>
                        <version>1.2.62</version>
                    </dependency>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.35</version>
                    </dependency>
                    <dependency>
                        <groupId>org.slf4j</groupId>
                        <artifactId>slf4j-log4j12</artifactId>
                        <version>1.7.28</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <!-- 理论上mysql8.0以上和以下的都支持，不过8.0以上的还没测试过，另外需要添加一些额外的设置-->
                    <url>jdbc:mysql://localhost:33306/mybatisPlus</url>
                    <username>root</username>
                    <password>123456</password>
                    <!--包名-->
                    <parent>com.example.demo_5</parent>
                    <include>
                        <!--要生成的表名-->
                        <value>user</value>
                    </include>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

#### \<configuration\>，如果配置了\<fileName\>则表示用外部文件去配置信息，以下所有字段都会失效，如果没有配置这个标签（如文件所示那样，建议用这种方式配置），以下字段会生效

| name                             | type           | info                                                                                                     | require |
| :------------------------------- | :------------- | :------------------------------------------------------------------------------------------------------- | :-----: |
| url                              | string         | 数据库的url                                                                                              |  必要   |
| username                         | string         | 用户名                                                                                                   |  必要   |
| password                         | string         | 密码                                                                                                     |  必要   |
| requireMapper                    | boolean        | 是否要生成mapper，默认为true                                                                             | 非必要  |
| requireEntity                    | boolean        | 是否要生成entity，默认为true                                                                             | 非必要  |
| requireController                | boolean        | 是否要生成controller，默认为false                                                                        | 非必要  |
| requireService                   | boolean        | 是否要生成service，默认为false                                                                           | 非必要  |
| requireServiceImpl               | boolean        | 是否要生成serviceImpl，默认为false                                                                       | 非必要  |
| requireXml                       | boolean        | 是否要生成xml，默认为true                                                                                | 非必要  |
| include                          | List\<String\> | 需要生成哪张表，例如上面的例子会对user表进行生成，如果不填这个参数将不会生成任何表(和用json文件配置不同) | 非必要  |
| author                           | string         | @author 的内容，默认codeGenerator                                                                        | 非必要  |
| swagger2                         | boolean        | 是否用swagger2注解，默认为false                                                                          | 非必要  |
| entityLombokModel                | boolean        | 是否用lombok注解，默认false                                                                              | 非必要  |
| restControllerStyle              | boolean        | 是否将controller注解转化为restController注解，默认false                                                  | 非必要  |
| entityTableFieldAnnotationEnable | boolean        | 是否使用jpa注解去标识各个字段，默认false                                                                 | 非必要  |
| parent                           | string         | service,xml,controller等等的包位置                                                                       | 非必要  |
| driverName                       | string         | 数据库驱动，默认为com.mysql.jdbc.Driver,如果想要配置8.0以上的mysql,需要手动加依赖                        | 非必要  |
| outputDir                        | string         | 输出的目录，默认为src/main/java，用${outputDir}+${parent}可以配置生成的文件放在何处                      | 非必要  |

#### 配置好的下一步，执行命令：mvn generate:execute

#### 可以看到左边的目录会生成一系列文件和目录

#### 生成的目录如下,图中绿色的表示生成的文件，其中service,entity,controller,mapper等都是生成的

##### entity展示

``` java
package com.example.demo_5.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author codeGenerator
 * @since 2020-01-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {


    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 详细信息
     */
    private Long infoId;


}
```

##### mapper展示

``` java
package com.example.demo_5.mapper;

import com.example.demo_5.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 *
 * @author codeGenerator
 * @since 2020-01-30
 */
public interface UserMapper extends BaseMapper<User> {

}
```

##### xml展示

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo_5.mapper.UserMapper">

</mapper>
```

##### service展示

``` java
package com.example.demo_5.service;

import com.example.demo_5.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  服务类
 *
 * @author codeGenerator
 * @since 2020-01-30
 */
public interface IUserService extends IService<User> {

}
```

##### serviceImpl展示

``` java
package com.example.demo_5.service.impl;

import com.example.demo_5.entity.User;
import com.example.demo_5.mapper.UserMapper;
import com.example.demo_5.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author codeGenerator
 * @since 2020-01-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
```

##### controller展示

``` java
package com.example.demo_5.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 *  前端控制器
 *
 * @author codeGenerator
 * @since 2020-01-30
 */
@Controller
@RequestMapping("/user")
public class UserController {

}

```

#### 这样的一套下来，不管是xml还是mapper都会比之前mybatis-generate生成的简单很多，而且附加功能很多，使用者只需要了解一下mybatis-plus的简单使用就可以了

#### 在每次执行完命令后控制台会输出一个json内容，如下

不支持在 Doc 外粘贴 block

#### 可以将其复制到一个文件中，然后配置\<fileName\>这样可以定制更多的生成规则，具体的定制方式url,如果链接失效了，可以自行google：mybatis-plus 代码生成器的配置

#### 注意这里的include,不填的会全库生成

不支持在 Doc 外粘贴 block

#### 关于如何将jar包安装到本地，输入以下命令即可

``` shell
mvn install:install-file -Dfile=/Users/zhuochuxin/mybatisPlusgenerate/target/generate-3.1.2.jar -DgroupId=com.jerryzhuo.mybatisPlus -DartifactId=generate -Dversion=3.1.2 -Dpackaging=jar
```

注意：将-Dfile=后面的路径改成jar包的文件路径即可
