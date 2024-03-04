package com.ssm.service.impl;

import com.ssm.service.BasicService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class BasicServiceImpl implements BasicService {

    @Override
    public ModelAndView basicInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/basic");
        modelAndView.addObject("name","cathy");
        modelAndView.addObject("sex","女");
        return modelAndView;
    }
}
