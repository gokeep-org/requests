package com.xuning.requests.core;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.xuning.requests.exception.RequestsException;


/****************************************
 * Copyright (c) xuning.
 * 尊重版权，禁止抄袭!
 * 如有违反，必将追究其法律责任.
 * @Auther is xuning on 2017/5/6.
 ****************************************/
public class HttpConnectionManager {
    /**
     * 数据库连接池配置信息
     * 参数信息说明：
     * maxTotalConnection：请求连接词的最大连接数，默认是6000，需要根据实际场景分析
     * defaultMaxPerRoute：默认路分发单节点数目，默认为一个节点，等于最大连接数
     * 这是一个单例，用来给HttpClient通过HttpClient连接池获取连接
     * 每一个连接信息都会打开和关闭，避免在高并发状态下发生线程阻塞。
     */
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;
    private static LayeredConnectionSocketFactory layeredConnectionSocketFactory = null;
    private static int maxTotalConnection = 6000;
    private static int defaultMaxPerRoute = 6000;

    /**
     * Http 连接池处理类
     * 该部分使用届台代码块中，节省时间效率
     */
    static {
        try {
            layeredConnectionSocketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            throw new RequestsException("new SSLConnection is error");
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", layeredConnectionSocketFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingHttpClientConnectionManager.setMaxTotal(maxTotalConnection);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
    }

    /**
     * 获取连接池中的连接对象
     * 提供给Apache HttpClient创建该对象信息，直接
     * HttpClient连接池中去拿
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        return httpClient;
    }

    public static int getMaxTotalConnection() {
        return maxTotalConnection;
    }

    public static void setMaxTotalConnection(int maxTotalConnection) {
        HttpConnectionManager.maxTotalConnection = maxTotalConnection;
    }

    public static int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public static void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        HttpConnectionManager.defaultMaxPerRoute = defaultMaxPerRoute;
    }

}
