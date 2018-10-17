## cvface-fe

### 项目介绍

人脸识别系统业务API

### 使用技术

springboot

mybatis

mysql

gradle

### 目录结构
```shell
├── src                                    // 项目源码，包含程序源码和测试源码
│   ├── main                    
│       ├── java               
│           ├ com
│               ├ cvface
│                   ├—— ccommon            // 公共目录（用于存放各种工具类、拦截器等等的目录）
│                   ├—— controller         // controller层存放目录
│                   ├—— dao                // 数据库交互层存放目录
│                   ├—— model              // 实体存放目录
│                   ├—— service            // 业务层存放目录
│                   ├—— Application.java   // SpringBoot项目启动文件
│       ├── resources                      // 项目配置文件存放目录
│           ├—— mappers                    // 存放mapper映射的文件目录（/**/*.xml）
│           ├—— application.properties
│           ├—— mybatis-config.xml
│   ├── test                               // 测试源码存放目录
├── .gitignore                             // 忽略提交文件配置
├── build.gradle                           // package.json
├── README.md                              // 项目简介与说明
├── settings.gradle                        // 针对module的全局配置，它的作用域所包含的所有module是通过settings.gradle来配置
```
### 项目安装与启动
- git clone git@gitee.com:cqut306edu/cvface-be.git
- 在电脑配置了gradle环境的前提下（如未配置环境，参考[gradle教程](https://www.yiibai.com/gradle/)），将项目导入开发工具，等待依赖下载完成。
- 运行Application.java启动项目
- 输入localhost:8089/product/getAllProducts（这里的端口自己设定，默认是8080，设定在application.properties文件中）

