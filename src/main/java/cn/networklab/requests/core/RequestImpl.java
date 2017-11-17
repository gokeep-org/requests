package cn.networklab.requests.core;


import java.util.Map;

import cn.networklab.requests.Requests;
import cn.networklab.requests.bean.HttpResponse;
import cn.networklab.requests.bean.data.Method;


public class RequestImpl implements Requests {

    @Override
    public HttpResponse get(String url) {
        return parseHttpMethod(Method.GET).execute(url);
    }

    @Override
    public HttpResponse get(String url, Map<String, String> params) {
        return parseHttpMethod(Method.GET).execute(url, params);
    }

    @Override
    public HttpResponse get(String url, Map<String, String> params, Map<String, String> headers) {
        return parseHttpMethod(Method.GET).execute(url, params, headers);
    }

    @Override
    public HttpResponse post(String url) {
        return parseHttpMethod(Method.POST).execute(url);
    }

    @Override
    public HttpResponse post(String url, Map<String, String> headers) {
        return parseHttpMethod(Method.POST).execute(url, headers);
    }

    @Override
    public HttpResponse post(String url, Map<String, String> params, Map<String, String> headers) {
        return parseHttpMethod(Method.POST).execute(url, params, headers);
    }

    @Override
    public HttpResponse post(String url, Object body, Map<String, String> headers) {
        return parseHttpMethod(Method.POST).execute(url, null, body, headers);
    }

    @Override
    public HttpResponse post(String url, Map<String, String> params, Object body, Map<String, String> headers) {
        return parseHttpMethod(Method.POST).execute(url, params, body, headers);
    }

    @Override
    public HttpResponse put(String url) {
        return parseHttpMethod(Method.PUT).execute(url);
    }

    @Override
    public HttpResponse put(String url, Map<String, String> headers) {
        return parseHttpMethod(Method.PUT).execute(url, headers);
    }

    @Override
    public HttpResponse put(String url, Map<String, String> params, Map<String, String> headers) {
        return parseHttpMethod(Method.PUT).execute(url, params, headers);
    }

    @Override
    public HttpResponse put(String url, Map<String, String> params, Object body, Map<String, String> headers) {
        return parseHttpMethod(Method.PUT).execute(url, params, body, headers);
    }

    @Override
    public HttpResponse put(String url, Object body, Map<String, String> headers) {
        return parseHttpMethod(Method.PUT).execute(url, null, body, headers);
    }

    @Override
    public HttpResponse delete(String url) {
        return parseHttpMethod(Method.DELETE).execute(url);
    }

    @Override
    public HttpResponse delete(String url, Map<String, String> headers) {
        return parseHttpMethod(Method.DELETE).execute(url, headers);
    }

    @Override
    public HttpResponse delete(String url, Map<String, String> params, Map<String, String> headers) {
        return parseHttpMethod(Method.DELETE).execute(url, params, headers);
    }

    @Override
    public HttpResponse delete(String url, Map<String, String> params, Object body, Map<String, String> headers) {
        return parseHttpMethod(Method.DELETE).execute(url, params, body, headers);
    }

    @Override
    public HttpResponse delete(String url, Object body, Map<String, String> headers) {
        return parseHttpMethod(Method.DELETE).execute(url, null, body, headers);
    }

    @Override
    public HttpResponse request(String url, String method) {
        return parseHttpMethod(method).execute(url);
    }

    @Override
    public HttpResponse request(String url, String method, Map<String, String> headers) {
        return parseHttpMethod(method).execute(url, headers);
    }

    @Override
    public HttpResponse request(String url, String method, Map<String, String> headers, Map<String, String> params) {
        return parseHttpMethod(method).execute(url, params, headers);
    }

    @Override
    public HttpResponse request(String url, String method, Map<String, String> params, Object body, Map<String, String> headers) {
        return parseHttpMethod(method).execute(url, params, body, headers);
    }

    @Override
    public HttpResponse request(String url, String method, Map<String, String> params, Map<String, Object> postForms, Object body, Map<String, String> headers) {
        return parseHttpMethod(method).execute(url, params, postForms, body, headers);
    }

    private RequestClient parseHttpMethod(String method) {
        switch (method.toUpperCase()) {
            case Method.GET:
                return new RequestGet();
            case Method.POST:
                return new cn.networklab.requests.core.RequestPost();
            case Method.PUT:
                return new cn.networklab.requests.core.RequestPut();
            case Method.DELETE:
                return new RequestDelete();
            default:
                return new RequestGet();
        }
    }
}