##cvface-fe

###项目介绍

人脸识别系统业务API

###使用技术

springboot

mybatis

mysql

gradle

###目录结构
├── build                       // webpack配置
│   ├── common.js               // webpack公共配置
│   ├── webpack.config.js       // 开发环境下的webpack配置
├── src                         // 代码
│   ├── app                     // 应用程序
│       ├── components          // 公共组件库
│       ├── modules             // 业务层
│   ├── app.component.tsx       // 最先加载的组件
│   ├── index.ts                // 导出app组件
├── global.d.ts                 // 配置全局可访问的声明文件
├── index.html                  // 模板html
├── index.tsx                   // 应用入口
├── .babelrc                    // babel配置
├── .gitignore                  // 忽略提交文件配置
├── package.json                // package.json
├── tsconfig.json               // ts配置
├── tslint.json                 // tslint配置