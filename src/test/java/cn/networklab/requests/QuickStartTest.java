package cn.networklab.requests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.networklab.requests.core.RequestImpl;

/**
 * Unit test for simple App.
 */
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
