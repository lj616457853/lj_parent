package com.guli.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 同一返回结果的一个类
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回的状态码")
    private Integer code;
    @ApiModelProperty(value = "返回的消息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private Map<String,Object> data=new HashMap<>();
    //把类的构造方法私有
    private R() {}
    //成功的静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    //失败的静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
