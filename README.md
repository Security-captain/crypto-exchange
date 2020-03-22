# crypto-exchange

#### 介绍
基于Java开发的比特币交易所 | BTC交易所 | ETH交易所 | 数字货币交易所 | 交易平台 | 撮合交易引擎。本项目基于SpringCloud微服务开发，可用来搭建和二次开发数字货币交易所，有完整的撮合交易引擎源码、后台管理（后端+前端）、前台（交易页面、活动页面、个人中心等）、安卓APP源码、苹果APP源码、币种钱包RPC源码。

#### 系统架构概要

![Framework整体说明](https://images.gitee.com/uploads/images/2020/0322/192334_8601c30c_2182501.png "微信截图_20200322192233.png")

#### 系统运行环境

1.  Centos 6.8
2.  MySQL 5.5.16
3.  Redis-x64-3.2.100
4.  Mongodb 3.6.13
5.  kafka_2.11-2.2.1
6.  nginx-1.16.0
7.  JRE 8u241
8.  JDK 1.8
9.  Vue
10. Zookeeper

#### 文件目录说明

 **00_framework** 

└─———admin   后台管理API 

└─———bitrade-job     任务管理 

└─———chat   OTC聊天 

└─———cloud  SpringCloud微服务管理 

└─———core   核心 

└─———exchange   撮合交易引擎 

└─———exchange-api   撮合交易API 

└─———exchange-core   撮合交易核心 

└─———jar   第三方类库 

└─———market    市场行情API、K线生成 

└─———otc-api   OTC交易API 

└─———otc-core  OTC核心 

└─———sql    SQL脚本 

└─———ucenter-api    用户个人中心API 

└─———wallet      钱包资产管理，负责与RPC对接 


 **01_wallet_rpc** 

└─———act

└─———bch

└─———bitcoin

└─———bsv

└─———btm

└─———ect

└─———eos

└─———erc-eusdt

└─———erc-token

└─———eth

└─———eth-support

└─———lib

└─———ltc

└─———rpc-common

└─———usdt

└─———xmr

 **02_App_Android** 
 **03_App_IOS** 
 **04_Web_Admin** 
 **05_Web_Front** 


#### 使用教程

1.  准备mysql数据库，创建名称为“xxxx”的数据库
2.  准备redis缓存数据库
3.  准备kafka流式处理环境（先配置运行zookper，接着配置运行kafka）
4.  准备mongodb数据库环境，创建用户admin、xxxx，创建bitrade数据库
5.  准备nginx，修改配置文件（可选，正式上线需配置）
6.  修改framework代码中的配置文件为准备环境配置参数
7.  编译生成jar可执行文件
8.  运行cloud.jar（微服务注册中心）
9.  运行market.jar（行情中心）
10. 运行exchange.jar（交易中心）
11. 运行ucenter.jar（用户中心）
12. 运行其他模块
13. 打开mysql，导入framework代码中的sql文件夹中xxxxxxx.sql文件，注意，trigger的sql如果报错，需要针对wallet表添加trigger
14. 运行前端vue项目
15. 运行后端vue项目
16. 运行钱包RPC
17. 运行自动交易机器人程序（本部分代码未上传，但不影响）

#### 注意事项

当内存不足时，在linux控制台输入top可以查看java进程占用了大量内存（一个java进程占用1G以上），因为有很多jar包需要运行，所以需要控制某些jar包使用的内存，目前控制以下4个：

java -jar -Xms128m -Xmx128m -Xmn200m -Xss256k  admin-api.jar

java -jar -Xms512m -Xmx512m -Xmn200m -Xss256k  cloud.jar

java -jar -Xms512m -Xmx512m -Xmn200m -Xss256k  wallet.jar

java -jar -Xms512m -Xmx512m -Xmn200m -Xss256k  activity.jar

#### 关于邮件&短信

1.  本系统支持邮件、短信发送系统运营状态
2.  系统通知/报警支持：用户注册、用户认证、用户充值/提现、币种RPC运行状态、系统资源使用监控等24种监控

#### 系统运行展示（PC前端）

![首页](https://images.gitee.com/uploads/images/2020/0322/193650_8e53953d_2182501.png "图片1.png")

![交易界面](https://images.gitee.com/uploads/images/2020/0322/193732_047f27a2_2182501.png "图片2.png")

![法币交易](https://images.gitee.com/uploads/images/2020/0322/193746_ad1d16e6_2182501.png "图片3.png")

![登录](https://images.gitee.com/uploads/images/2020/0322/193759_edc5dc7b_2182501.png "图片5.png")

![活动页面](https://images.gitee.com/uploads/images/2020/0322/193839_6f18a03d_2182501.png "图片7.png")

![公告](https://images.gitee.com/uploads/images/2020/0322/193852_3ad12a6f_2182501.png "图片8.png")

![帮助](https://images.gitee.com/uploads/images/2020/0322/193902_ef09925e_2182501.png "图片9.png")

#### 系统运行展示（APP前端）

![首页](https://images.gitee.com/uploads/images/2020/0322/193927_9940ca7c_2182501.jpeg "图片10.jpg")

![行情](https://images.gitee.com/uploads/images/2020/0322/193941_ff5a16a2_2182501.jpeg "图片11.jpg")

![K线](https://images.gitee.com/uploads/images/2020/0322/193951_abf7b5b6_2182501.jpeg "图片12.jpg")

![交易](https://images.gitee.com/uploads/images/2020/0322/194003_d14a772a_2182501.jpeg "图片13.jpg")

![个人中心](https://images.gitee.com/uploads/images/2020/0322/194021_a047d3a5_2182501.jpeg "图片14.jpg")

![个人资产管理](https://images.gitee.com/uploads/images/2020/0322/194059_faeeeb4a_2182501.jpeg "图片15.jpg")

![邀请管理](https://images.gitee.com/uploads/images/2020/0322/194112_7ae11b00_2182501.jpeg "图片16.jpg")

#### 系统运行展示（后端）

![登录](https://images.gitee.com/uploads/images/2020/0322/194251_9b5293ff_2182501.png "图片17.png")

![首页](https://images.gitee.com/uploads/images/2020/0322/194305_f83e4f90_2182501.png "图片18.png")

![用户管理](https://images.gitee.com/uploads/images/2020/0322/194321_73eb8f58_2182501.png "图片19.png")

![邀请管理](https://images.gitee.com/uploads/images/2020/0322/194337_fd257186_2182501.png "图片20.png")

![法币交易订单管理](https://images.gitee.com/uploads/images/2020/0322/194406_ebe7328d_2182501.png "图片21.png")

![首页Banner管理](https://images.gitee.com/uploads/images/2020/0322/194433_4fb39b0a_2182501.png "图片22.png")

![交易对管理](https://images.gitee.com/uploads/images/2020/0322/194450_1eb7bb6f_2182501.png "图片23.png")

![活动管理](https://images.gitee.com/uploads/images/2020/0322/194505_204d23ce_2182501.png "图片24.png")

![红包管理](https://images.gitee.com/uploads/images/2020/0322/194531_e12eb93a_2182501.png "图片25.png")

![币种管理](https://images.gitee.com/uploads/images/2020/0322/194618_fe17409a_2182501.png "图片26.png")

![OTC管理，后端开发完成，前端未对接](https://images.gitee.com/uploads/images/2020/0322/194654_bd0acbe7_2182501.png "图片27.png")

#### 补充说明

本数字货币交易系统系我所在公司为交易所开发的项目，该交易所因团队原因已停止运营，我司也已于2月解散。因我参与项目时，负责整体研发管理、架构设计以及客户对接，所以掌握所有代码。

另，本人提供有偿技术帮助!
QQ：877070886