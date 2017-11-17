package cn.networklab.requests.bean.data;

public class Body {
    private String bodyJson;
    private Object bodyObject;


    public Object getBodyObject() {
        return bodyObject;
    }

    public Body setBodyObject(Object bodyObject) {
        this.bodyObject = bodyObject;
        return this;
    }

    public String getBodyJson() {
        return bodyJson;
    }

    public void setBodyJson(String bodyJson) {
        this.bodyJson = bodyJson;
    }
}
