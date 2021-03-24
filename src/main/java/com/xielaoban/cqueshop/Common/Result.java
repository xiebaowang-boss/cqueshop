package com.xielaoban.cqueshop.Common;

import lombok.Data;

/**
 * @Author 蟹老板
 * @Date 2020/11/5 16:50
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Common
 * @Description
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result Success() {
        return new Result(1, "success", null);
    }

    public static Result Success(Object data) {
        return new Result(1, "success", data);
    }

    public static Result Error() {
        return new Result(0, "error", null);
    }
}
