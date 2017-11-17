package cn.networklab.requests;


import java.util.Map;

import cn.networklab.requests.bean.HttpResponse;

/**
 *Requests 框架接口
 * 支持：GET，POST, PUT, DELETE 方法请求
 * 建议：使用该框架发送Restful类型请求
 * 默认实例化时使用默认的实现即可，Requests client = new Requests();
 */
public interface Requests {

    public HttpResponse get(
            String url
    );

    public HttpResponse get(
            String url, Map<String, String> params
    );

    public HttpResponse get(
            String url, Map<String, String> params, Map<String, String> headers
    );

    public HttpResponse post(
            String url
    );

    public HttpResponse post(
            String url, Map<String, String> headers
    );

    public HttpResponse post(
            String url, Map<String, String> params, Map<String, String> headers
    );


    public HttpResponse post(
            String url, Object body, Map<String, String> headers
    );

    public HttpResponse post(
            String url, Map<String, String> params, Object body, Map<String, String> headers
    );

    public HttpResponse put(
            String url
    );

    public HttpResponse put(
            String url, Map<String, String> headers
    );

    public HttpResponse put(
            String url, Map<String, String> params, Map<String, String> headers
    );

    public HttpResponse put(
            String url, Map<String, String> params, Object body, Map<String, String> headers
    );

    public HttpResponse put(
            String url, Object body, Map<String, String> headers
    );

    public HttpResponse delete(
            String url
    );

    public HttpResponse delete(
            String url, Map<String, String> headers
    );

    public HttpResponse delete(
            String url, Map<String, String> params, Map<String, String> headers
    );

    public HttpResponse delete(
            String url, Map<String, String> params, Object body, Map<String, String> headers
    );

    public HttpResponse delete(
            String url, Object body, Map<String, String> headers
    );

    public HttpResponse request(
            String url, String method
    );

    public HttpResponse request(
            String url, String method, Map<String, String> headers
    );

    public HttpResponse request(
            String url, String method, Map<String, String> params, Map<String, String> headers
    );

    public HttpResponse request(
            String url, String method, Map<String, String> params, Object body, Map<String, String> headers
    );

    public HttpResponse request(
            String url, String method, Map<String, String> params, Map<String, Object> postForms, Object body, Map<String, String> headers
    );
}