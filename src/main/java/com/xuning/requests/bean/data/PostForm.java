package com.xuning.requests.bean.data;

import org.apache.http.NameValuePair;

public class PostForm implements NameValuePair {
    private String key;
    private String value;

    public PostForm(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getName() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
