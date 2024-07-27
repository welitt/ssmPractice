package com.ssm.service.impl;

import com.ssm.constant.UserInfoConstant;
import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;
import com.ssm.service.BasicService;
import com.ssm.task.ImportUserTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Service
public class BasicServiceImpl implements BasicService {
    @Resource
    private UserMapper userMapper;

    @Transactional
    @Override
    public ModelAndView basicInfo() {
        User user = userMapper.findUserById(1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/basic");
        modelAndView.addObject("name",user.getName());
        modelAndView.addObject("constellationType",user.getConstellationType());
        return modelAndView;
    }

    @Override
    public String importUser(String organization, MultipartFile file) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        List<User> userList = new ArrayList<>();
        userList.add(new User(null,"苏菲亚公主", UserInfoConstant.ConstellationType.Scorpio));
        userList.add(new User(null,"安柏公主", UserInfoConstant.ConstellationType.Pisces));

        List<FutureTask<User>> callbacks = new ArrayList<>();
        for(User user : userList){
            FutureTask<User> userFutureTask = new FutureTask<>(new ImportUserTask(userMapper,user));
            service.execute(userFutureTask);
            callbacks.add(userFutureTask);
        }

        int successCnt = 0,failCnt = 0;
        for(FutureTask<User> userFutureTask : callbacks){
            User user = userFutureTask.get();
            if(user.getId() == null){
                failCnt ++;
            }else
                successCnt++;
        }
        return "导入成功："+successCnt+"！导入失败："+failCnt;
    }
}
