package cn.networklab.requests.core;

import cn.networklab.requests.Requests;

/**
 * 自定义构建客户端
 */
public class RequestsBuilder {
    private RequestsBuilder(){
    }
    private static RequestsBuilder requestsBuilder;

    static {
        requestsBuilder = new RequestsBuilder();
    }


    public static Requests createDefaultClient(){
        return new RequestImpl();
    }

    public static Requests createClient(){
        return new RequestImpl();
    }

    public static RequestsBuilder custom(){
        return requestsBuilder;
    }

    public static RequestsBuilder maxConnectionSize(int size){
        HttpConnectionManager.setMaxTotalConnection(size);
        return requestsBuilder;
    }

    public static RequestsBuilder maxPerRouteSize(int size){
        HttpConnectionManager.setDefaultMaxPerRoute(size);
        return requestsBuilder;
    }

    public static RequestsBuilder connectionTimout(int timeOut){
        DefaultConfig.CONNECTION_TIMOUT = timeOut;
        return requestsBuilder;
    }
}
