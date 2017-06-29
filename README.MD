# requests

###1.快速开始

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
        LOGGER.info("client end");
    }
```

