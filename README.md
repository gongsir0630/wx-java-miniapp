<h1 align="center">wx-java-miniapp</h1>
<p align="center">
    <a href="https://www.travis-ci.com/gongsir0630/wx-java-miniapp">
        <img src="https://www.travis-ci.com/gongsir0630/wx-java-miniapp.svg?branch=main" alt="Build Status" />
    </a>
</p>

## 前言
本项目使用 [WxJava](https://github.com/Wechat-Group/WxJava) 对接微信小程序后台开发，基于SpringBoot构建，实现微信小程序后端开发功能，支持多个小程序。

更多信息请查阅：[https://github.com/Wechat-Group/WxJava](https://github.com/Wechat-Group/WxJava)

## 技术栈
* SpringBoot -> 环境搭建
* MySQL -> 存储wx-miniapp配置信息(appid,appsecret等)
* Druid -> 连接池
* MyBatis-Plus -> 基本的CRUD
* Lombok -> 简化开发

## 使用
0. 安装环境, 执行sql脚本
1. 克隆项目
2. 修改配置
3. 部署运行