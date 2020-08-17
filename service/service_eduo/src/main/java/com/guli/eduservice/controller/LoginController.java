package com.guli.eduservice.controller;

import com.guli.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(description = "用户登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//表示可以跨域
public class LoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admin").data("roles","[admin]").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
