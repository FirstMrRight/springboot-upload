package com.example.springbootupload.fs;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RestController
public class FileController {
    @Autowired
    private CommonFileUtil fileUtil;

    @Autowired
    private FdfsConfig fdfsConfig;

    // 使用fastdfs进行文件上传
    @PostMapping("/uploadFileToFast")
    public String uoloadFileToFast(@RequestParam("fileName")MultipartFile file, RedirectAttributes attributes) throws IOException{

        if(file.isEmpty()){
            log.info("文件不存在");
        }
        String path = fileUtil.uploadFile(file);
        String url = fdfsConfig.getResHost()+path;
        attributes.addAttribute("url", url);
        return "redirect:/success";
    }

}