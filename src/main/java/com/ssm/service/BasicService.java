package com.ssm.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutionException;

public interface BasicService {
    ModelAndView basicInfo();

    String importUser(String organization, MultipartFile file) throws ExecutionException, InterruptedException;
}
