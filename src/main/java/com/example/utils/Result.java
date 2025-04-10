package com.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(Integer code,T data){
        return new Result<>(code, "操作成功", data);
    }

    public static <T> Result<T> success(){
        return new Result<>(200, "操作成功", null);
    }

    public static <T> Result<T> error(Integer code,String message){
        return new Result<>(code, message, null);
    }
}
