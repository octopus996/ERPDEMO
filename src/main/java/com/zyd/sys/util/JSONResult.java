package com.zyd.sys.util;

public class JSONResult {

    /**
     *
     */
    private String message;
    /**
     * 执行是否成功
     * true:成功
     * false:失败
     */
    private boolean success;

    public JSONResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public JSONResult() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
