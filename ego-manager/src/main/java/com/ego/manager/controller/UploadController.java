package com.ego.manager.controller;

import com.ego.common.result.FileResult;
import com.ego.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author MH
 * @site  文件上传Controller
 * @company
 * @create 2020-03-19-11:34
 */
@Controller
@RequestMapping("fileUpload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("save")
    @ResponseBody
    private FileResult upload(MultipartFile file) throws IOException{
        //System.out.println(file.getName());
        //System.out.println(file.getOriginalFilename());
        String filename = file.getOriginalFilename();
        //格式化时间
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        filename = date+System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
        return uploadService.upload(file.getInputStream(),filename);
    }
}