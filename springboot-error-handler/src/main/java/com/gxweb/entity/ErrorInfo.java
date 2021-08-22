package com.gxweb.entity;
/**
 * 主要用途：存储错误信息的Bean.
 * 设计说明：
 * 1.官方提供了默认错误存储{@link DefaultErrorAttributes},但其最终返回的是存储结构是Map<String,Object>。
 * 2.为了避免采用Map这种模糊的数据结构,故统一使用{@link ErrorInfo}来表示错误信息。
 * 3.作为存储Bean,最好不要耦合业务逻辑,故统一使用{@link ErrorInfoBuilder}来构建错误信息.
 *
 */
public class ErrorInfo {

    private String time;//发生时间
    private String url;//访问Url
    private String error;//错误类型
    String  stackTrace;//错误的堆栈轨迹
    private int statusCode;//状态码
    private String reasonPhrase;//状态码 原因短语

    /**
     * Getters And Setters
     */
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}