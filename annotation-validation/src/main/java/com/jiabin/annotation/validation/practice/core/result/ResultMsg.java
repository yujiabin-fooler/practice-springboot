package com.jiabin.annotation.validation.practice.core.result;

/**
 * <p>
 * ResultMsg
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/5/24
 */
public class ResultMsg<T> {

    /**状态码**/
    private int code;

    /**结果描述**/
    private String message;

    /**结果集**/
    private T data;

    /**时间戳**/
    private long timestamp;

    public int getCode() {
        return code;
    }

    public ResultMsg<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultMsg<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultMsg<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ResultMsg<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResultMsg() {
        timestamp = System.currentTimeMillis();
    }

    public static <T> ResultMsg<T> success() {
        return success(null);
    }

    public static <T> ResultMsg<T> success(T data) {
        ResultMsg<T> resultMsg = new ResultMsg<>();
        resultMsg.setCode(200);
        resultMsg.setData(data);
        return resultMsg;
    }

    public static <T> ResultMsg<T> fail(String message) {
        return fail(500, message);
    }

    public static <T> ResultMsg<T> fail(int code, String message) {
        ResultMsg<T> resultMsg = new ResultMsg<>();
        resultMsg.setCode(code);
        resultMsg.setMessage(message);
        return resultMsg;
    }
}
