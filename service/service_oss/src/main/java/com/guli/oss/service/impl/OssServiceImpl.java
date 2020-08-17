package com.guli.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.guli.oss.service.OssService;
import com.guli.oss.utils.ConstandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
public class OssServiceImpl implements OssService {

    //上传头像
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 通过工具类获取这些值
        String endpoint = ConstandPropertiesUtils.END_POINT;
        String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstandPropertiesUtils.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = null;
        //第一个参数 : BucketName
        //第二参数 : 上传到oss文件路径和名称
        try {
            //获取文件名称
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            //2. 把文件按照日期进行分类
            //2019/11/12/01.jpg
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName =datePath+"/"+fileName;
            putObjectRequest = new PutObjectRequest(bucketName, fileName,file.getInputStream());
            // 上传文件。
            ossClient.putObject(putObjectRequest);

// 关闭OSSClient。
            ossClient.shutdown();
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);
    }
}
