package com.huanleichen.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult implements Serializable {
    private static String RESULT_OK = "ok";
    private static String RESULT_NOT_OK = "not_ok";
    private static String SUCCESS = "成功操作";

    private String result;
    private Object data;
    private Cursor cursor;
    private String success;
    private List<Error> errors;

    public static BaseResult notOk(List<Error> errors) {
        return new BaseResult(RESULT_NOT_OK, null, null, null, errors);
    }

    public static BaseResult ok() {
        return new BaseResult(RESULT_OK, null, null, SUCCESS, null);
    }

    public static BaseResult ok(Object data) {
        return new BaseResult(RESULT_OK, data, null, SUCCESS, null);
    }

    public static BaseResult ok(Object data, String success) {
        return new BaseResult(RESULT_OK, data, null, success, null);
    }

    public static BaseResult ok(String success) {
        return new BaseResult(RESULT_OK, null, null, success, null);
    }

    public static BaseResult ok(Object data, String success, Cursor cursor) {
        return new BaseResult(RESULT_OK, data, cursor, success, null);
    }

    @Data
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error {
        private String field;
        private String message;
    }

}
