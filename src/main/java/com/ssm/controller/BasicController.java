package com.ssm.controller;

import com.ssm.service.BasicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {
    @Resource
    private BasicService basicService;

    @RequestMapping(value = "/getName")
    public ModelAndView getName(){
        return basicService.basicInfo();
    }

}
