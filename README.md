# spring-cloud-sale-system-example

#### 一个基于spring-cloud分布式权限控制，资源访问校验，微服务治理解决方案的综合案例

> 服务名称

| 服务名称 | addr-url | 备注 |
| :-----| :---- | :---- |
| OAUTH2-SERVER | 192.168.0.102:7000 |oauth2授权服务器|
| CLOUD-ADMIN-SERVER | 192.168.0.102:7001 | cloud-admin |
| ADDRESS-SERVER | 192.168.0.102:8081 |地址信息API|
| FILM-SERVER | 192.168.0.102:8083 |影片信息API|
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

#### oauth_client_details 表
```mysql

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
``` 
#### oauth_client_details表里的数据如下
```json
[{
	"client_id": "android",
	"resource_ids": "oauth2-resource",
	"client_secret": "123456",
	"scope": "all",
	"authorized_grant_types": "password",
	"web_server_redirect_uri": null,
	"authorities": "ROLE_ADMIN",
	"access_token_validity": 7400,
	"refresh_token_validity": 7400,
	"additional_information": null,
	"autoapprove": null
}]
```

#### 这里配置好了之后我们的访问路径为:
> 常用post请求方式
> 这里我们用basic Auth的方式。需要填写Username:android，Password:123456
> http://192.168.0.100:9001/auth/oauth/token?username=admin&password=123456&grant_type=password

```text
{
    "access_token": "6b4f8ccf-6262-4ab2-9816-916cb939849f",
    "token_type": "bearer",
    "expires_in": 5358,
    "scope": "all"
}
```
#### 带access_token的访问的URL:

> http://localhost:9001/film/api/show?access_token=6b4f8ccf-6262-4ab2-9816-916cb939849f
```java
    /***
     *无权限访问
     */
    @GetMapping("/show")
    public String show() {
        log.info("show");
        return "show";
    }
```
#### 有权限返回show

```text
show
```
#### 带access_token的访问的URL:

>http://localhost:9001/film/api/test?access_token=6b4f8ccf-6262-4ab2-9816-916cb939849f

```java
    /***
     *不允许访问
     */
    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String test() {
        log.info("test");
        return "test";
    }
```
#### 无权限返回,不允许访问

```xml
<oauth>
 <error_description>不允许访问</error_description>
 <error>access_denied</error>
</oauth>
```
