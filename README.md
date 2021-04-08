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
* Travis CI 持续集成

## 使用
### 0. 安装环境, 执行sql脚本
* 安装 mysql
* 安装 redis
* 执行`sql/wx-java-miniapp.sql`脚本文件(推荐使用 Navicat)
### 1. 克隆项目
```shell
git clone https://github.com/gongsir0630/wx-java-miniapp.git
```
### 2. 修改配置
* 修改`src/main/resources/application-*.yml`配置文件中的 redis 和 mysql 配置
### 3. 部署运行
```shell
mvn spring-boot:run
```
### 4. 持续集成
关于 Travis CI 持续集成和自动部署请参考 [使用 Travis 打造 SpringBoot 应用持续集成和自动部署 | Travis CI 初体验](https://juejin.cn/post/6946220560938434596)