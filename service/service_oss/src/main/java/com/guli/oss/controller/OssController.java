package com.guli.oss.controller;

import com.guli.commonutils.R;
import com.guli.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    OssService service;
    //上传头像
    @PostMapping
    public R uploadOssFile(MultipartFile file){
      //方法返回，上传到OSS的路径
        String url =  service.uploadFileAvatar(file);
        //获取上传的文件
        return R.ok().data("url",url);
    }
}
