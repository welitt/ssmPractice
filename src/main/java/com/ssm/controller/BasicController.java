package com.ssm.controller;

import com.ssm.service.BasicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {
    @Resource
    private BasicService basicService;

    @RequestMapping(value = "/getName")
    public ModelAndView getName(){
        return basicService.basicInfo();
    }

    /**
     * 测试多线程导入用户
     * @param organization
     * @param file
     */
    @RequestMapping(value="/import/user")
    @ResponseBody
    public String importUser(@RequestParam("organization") String  organization,
                           @RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        return basicService.importUser(organization,file);
    }

}
