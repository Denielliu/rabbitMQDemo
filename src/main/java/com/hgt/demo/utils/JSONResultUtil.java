package com.hgt.demo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Title: JSONResultUntil.java
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户用的
 *				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 *  			其他自行处理
 *  			200：表示成功
 *  			500：表示错误，错误信息在msg字段中
 *  			501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 * @Author 柳强
 * @Date 2019/7/9 11:02
 * @Version V1.0
 * @Modified By:
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@ApiModel(description = "Json返回值处理类")
public class JSONResultUtil {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @ApiModelProperty(value = "响应业务状态")
    private Integer status;

    @ApiModelProperty(value = "响应消息")
    private String msg;

    @ApiModelProperty(value = "响应中的数据")
    private Object data;

    @ApiModelProperty(value = "不使用")
    private String ok;

    public static JSONResultUtil build(Integer status, String msg, Object data) {
        return new JSONResultUtil(status, msg, data);
    }

    public static JSONResultUtil ok(Object data) {
        return new JSONResultUtil(data);
    }

    public static JSONResultUtil ok() {
        return new JSONResultUtil(null);
    }
    
    public static JSONResultUtil errorMsg(String msg) {
        return new JSONResultUtil(500, msg, null);
    }
    
    public static JSONResultUtil errorMap(Object data) {
        return new JSONResultUtil(501, "error", data);
    }
    
    public static JSONResultUtil errorTokenMsg(String msg) {
        return new JSONResultUtil(502, msg, null);
    }
    
    public static JSONResultUtil errorException(String msg) {
        return new JSONResultUtil(555, msg, null);
    }

    public JSONResultUtil() {

    }

    public JSONResultUtil(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONResultUtil(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 
     * @Description: 将json结果集转化为JSONResultUntil对象
     * 				需要转换的对象是一个类
     * @param jsonData
     * @param clazz
     * @return
     * 
     */
    public static JSONResultUtil formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JSONResultUtil.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @Description: 没有object对象的转化
     * @param json
     * @return
     */
    public static JSONResultUtil format(String json) {
        try {
            return MAPPER.readValue(json, JSONResultUtil.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @Description: Object是集合转化
     * 				需要转换的对象是一个list
     * @param jsonData
     * @param clazz
     * @return
     */
    public static JSONResultUtil formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
