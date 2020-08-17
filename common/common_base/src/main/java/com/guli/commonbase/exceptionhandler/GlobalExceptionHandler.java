package com.guli.commonbase.exceptionhandler;


import com.guli.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了能够返回数据
    public R error(Exception e){
         e.printStackTrace();
         return R.error().message("执行全局异常");
    }
    //自定义异常
    //指定什么异常执行这个方法
    @ExceptionHandler(GuLiException.class)
    @ResponseBody//为了能够返回数据
    public R error(GuLiException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
