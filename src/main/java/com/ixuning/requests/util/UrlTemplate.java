package com.ixuning.requests.util;

public final class UrlTemplate {

    private String template;
    private String urlString;

    public UrlTemplate(String template) {
        this.template = template;
    }

    /**
     * 构建字符串，主要为URL
     * 这里本可以装换为URL，但是为了考虑可扩展性
     * 这里为返回String类型
     */
    public String build(String base, Object... values) {
        this.urlString = String.format(base + this.template, values);
        return this.urlString;
    }

    public String buildWithQuery(String base, String queryString, Object... values) {
        urlString = String.format(base + this.template, values) + queryString;
        return urlString;
    }

    public String setQuery(String query, Object... objects) {
        String resUrl = urlString + String.format(query, objects);
        return resUrl;
    }
}
