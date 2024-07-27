package com.ssm.controller;

import com.ssm.util.DocxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FileController {

    /**
     * 2.验证DocumentDocument.class类重复的bug以及解决方法
     */
    @RequestMapping("/file/docx/read")
    @ResponseBody
    public void docxRead(){
        DocxUtil.read("F:\\Users\\Welit\\Desktop\\简历\\简历_初始模板.docx");
    }
}
