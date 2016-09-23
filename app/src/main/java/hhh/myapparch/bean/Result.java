package hhh.myapparch.bean;

/**
 * Created by hhh on 2016/6/12.
 */
public class Result<T> {
    public static final int STATE_SUC=0;
    public static final int STATE_FAIL=1;

    private int code;
    private String message;
    private T[] data;

    public Result(){}

    public Result(int code, String message, T[] data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }
}
