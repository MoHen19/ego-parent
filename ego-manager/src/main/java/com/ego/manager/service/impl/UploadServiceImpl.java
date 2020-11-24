package com.ego.manager.service.impl;

import com.ego.common.result.FileResult;
import com.ego.manager.service.UploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author MH
 * @site 上传实现类
 * @company
 * @create 2020-03-19-11:59
 */
@Service
public class UploadServiceImpl implements UploadService {

    /**
     * 上传
     * @param inputStream
     * @param filename
     * @return
     */
    @Override
    public FileResult upload(InputStream inputStream, String filename) {
        FileResult fileResult = new FileResult();

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "ZoDp4L7xSZrsoOwlLYzv-P5TropdEb6Z53z5GGdy";
        String secretKey = "fklLf1xoOa8LyW5XC67J4_8cZp9JOp9ziLGYJTdJ";
        String bucket = "mh19";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filename;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                // DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                // System.out.println(putRet.key);
                // System.out.println(putRet.hash);
                if (response.statusCode == 200) {
                    fileResult.setSuccess("success");
                    fileResult.setMessage("上传成功");
                    fileResult.setFileUrl("http://q6ynrt3zn.bkt.clouddn.com/" + filename);
                    return fileResult ;
                }else{
                    fileResult.setSuccess("error");
                    fileResult.setMessage("上传失败");
                    return fileResult ;
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                fileResult.setError("error");
                fileResult.setMessage("上传失败");
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileResult;
    }
}
