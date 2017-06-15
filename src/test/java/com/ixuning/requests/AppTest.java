package com.ixuning.requests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuning.requests.Requests;
import com.xuning.requests.core.RequestImpl;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static Requests requests;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

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
