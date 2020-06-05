# spring-cloud-sale-system-example

#### 一个基于spring-cloud分布式权限控制，资源访问校验，微服务治理解决方案的综合案例

> 服务名称

| 服务名称 | addr-url | 备注 |
| :-----| :---- | :---- |
| CLOUD-ADMIN-SERVER | 192.168.0.102:8099 | cloud-admin |
| ADDRESS-SERVER | 192.168.0.102:8081 |地址信息API|
| OAUTH2-SERVER | 192.168.0.102:9002 |oauth2|
| EUREKA-SERVER | 192.168.0.102:9000 | eureka-server|
| ZUUL-OAUTH-GATEWAY | 192.168.0.102:9001 | gateway|

>打包结果
```text
[INFO] Building jar: /Users/yichuan/IdeaProjects/springcloud-alibaba-salesystem-example/cloud-sleuth/target/clould-sleuth-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for springcloud-alibaba-salesystem-example 1.0-SNAPSHOT:
[INFO] 
[INFO] springcloud-alibaba-salesystem-example ............. SUCCESS [  0.010 s]
[INFO] commons ............................................ SUCCESS [ 11.777 s]
[INFO] oauth-gateway ...................................... SUCCESS [  5.119 s]
[INFO] film-server ........................................ SUCCESS [  0.394 s]
[INFO] staff-server ....................................... SUCCESS [  0.340 s]
[INFO] payment-server ..................................... SUCCESS [  0.390 s]
[INFO] store-server ....................................... SUCCESS [  0.415 s]
[INFO] address-server ..................................... SUCCESS [  2.602 s]
[INFO] auth-server ........................................ SUCCESS [  6.255 s]
[INFO] cache .............................................. SUCCESS [  0.317 s]
[INFO] commons-message-mq ................................. SUCCESS [  0.343 s]
[INFO] cloud-bus .......................................... SUCCESS [  0.336 s]
[INFO] clould-sleuth ...................................... SUCCESS [  0.437 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS

```


