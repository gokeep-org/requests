package com.xuning.requests.bean.data;

import java.io.InputStream;
import java.util.UUID;

public interface DataType {
    public static final String falg = UUID.randomUUID().toString();

    public String json();

    public String text();

    public InputStream inputStream();

    public int statusCode();

    public Object toObject(Class classes);
}