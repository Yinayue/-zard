package com.example.demo.util.basic;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class JsonResult<T> {
    private Long code;
    private String msg;
    private T data;
    private static JSONObject result = new JSONObject();

    protected JsonResult() {
    }

    protected JsonResult(long code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param  message 提示信息
     */
    public static String success(String message) {
        result.put("code",200);
        result.put("msg",message);
        return result.toJSONString();
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> String success(T data) {
        result.put("code",200);
        result.put("msg","success");
        result.put("data",data);
        return result.toJSONString();
    }


    /**
     * 失败返回结果
     */
    public static <T> String error() {
        result.put("code",400);
        result.put("msg","failed");
        return result.toJSONString();
    }

    /**
     * 失败返回结果
     */


}


