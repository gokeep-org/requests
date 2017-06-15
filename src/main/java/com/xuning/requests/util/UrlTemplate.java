package com.xuning.requests.util;

public class UrlTemplate {

    private String template;
    private String urlString;

    public UrlTemplate(String template) {
        this.template = template;
    }

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
