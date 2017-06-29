# Requests
==作者： 徐宁==
==Email： 1748373312@qq.com==

[TOC]
###简介
	本项目为java版本的requests实现，为Http请求客户端实现，支持rest，内部实现连接池，支持多样化配置，对项目无依赖，即插即拔，能够像python的requests那样快速开发而又不失灵活且无侵入。
###1.快速实践

####1-1.maven依赖

```xml
<dependency>
    <groupId>cn.networklab</groupId>
    <artifactId>requests</artifactId>
    <version>${version}</version>
</dependency>
```
####1-2.最佳实践
```java
public class QuickStartTest {
    public static Requests client;
    private static final Logger LOGGER = LoggerFactory.getLogger(QuickStartTest.class);
    /**
     * 第一步： 实例化client对象，建议设为单例
     */
    @Before
    public void before() {
        client = new RequestImpl();
    }
    /**
     * 第二步：发送请求，获取
     */
    @Test
    public void sendSimpleRequest() {
        String res = client.get("http://www.baidu.com").text();
        LOGGER.info(res);
    }
    @After
    public void destory(){
        LOGGER.info("client request end");
    }
}
```
###2.请求客户端Client
	客户端以借口Requests引用，框架已经实现了客户端，就是RequestsImpl，当然您可以自己去重写，您可以直接代码中引用或者使用spring注入的方式，因为request客户端消耗一定的资源，建议客户端构建为单例。调用如下代码所示：
```java
Requests requestClient = new RequestImpl();
```

###3.Requests支持的请求方法
    1.GET:       RequestClient.get({url}, {params}, {headers})
    2.POST:	  RequestClient.post({url}, {params}, {bosy}, {headers})
    3.PUT:       RequestClient.post({url}, {params}, {bosy}, {headers})
    4.DELETE:	RequestClient.post({url}, {params}, {bosy}, {headers})
###4.结果类型
	1.text（文本）：	RequestClient.get({url}, {params}, {headers}).text();
	2.json（json）：	RequestClient.get({url}, {params}, {headers}).json();
	3.inputstream(流)： RequestClient.get({url}, {params}, {headers}).inputStream();
###5.框架流程
	Request->RequestClient->HttpConnectionManager->HttpMethod->HttpResponse