package com.huanleichen.itoken.common.contants;

public enum HttpStatusContants {
    BAD_GATEWAY(502, "请求上游服务失败");

    private int status;
    private String content;

    HttpStatusContants(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

}
