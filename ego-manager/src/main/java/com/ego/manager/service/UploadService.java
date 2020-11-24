package com.ego.manager.service;

import com.ego.common.result.FileResult;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-19-11:56
 */
@Service
public interface UploadService {
    /**
     * 上传
     * @return
     */
    FileResult upload(InputStream inputStream, String filename);
}
