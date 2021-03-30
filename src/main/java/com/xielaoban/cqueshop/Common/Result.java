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
public class Result {
    private Integer code;
    private String msg;
    private Object data;


    Result(Integer code, String msg, Object data) {
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

    public static Result Success(String msg, Object data) {
        return new Result(1, msg, data);
    }

    public static Result Error() {
        return new Result(0, "error", null);
    }

    public static Result Error(Object data) {
        return new Result(0, "error", data);
    }

    public static Result Error(String msg, Object data) {
        return new Result(0, msg, data);
    }
}
