SpringBoot Demo
====

## How to start
change the db config in application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/springboot_demo?serverTimezone=Asia/Shanghai&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=
```
run com.test.demo.Application.main

## doc

API: http://localhost:8081/api

文档： http://blog.rmiao.top/Hello-Java-Web/


## Architecture

### 数据库

MySQL, 简单，开源，易上手；

### 后台服务端

**web Framework**： 

Springboot,  简单，强大，开源，易上手；


**持久层 Framework**:

JPA, 简单，强大。也可以使用Mybatis。


**远程调用 Framework**: 

OpenFeign/feign， 声明式编程，结合hystrix利器。

**断路器**：

Hystrix, 业界楷模。


开发模式： 

领域驱动设计DDD



### 前端

**构建工具**:
webpack, 模块化，打包，支持es6。


**模板语言**：
thymeleaf， Springboot官方推荐。

**界面库**:
ReactJS, 前端流行。

**数据交互**：
Redux，ReactJS常用配置。






