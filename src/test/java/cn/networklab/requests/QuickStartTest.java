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
    public static Requests requests;
    private static final Logger LOGGER = LoggerFactory.getLogger(QuickStartTest.class);

    @Before
    public void before() {
        requests = new RequestImpl();
    }

    @Test
    public void sendSimpleRequest() {
        String res = requests.get("http://www.baidu.com").text();
        LOGGER.info(res);
    }

    @After
    public void destory(){
        LOGGER.info("requests end");
    }
}
