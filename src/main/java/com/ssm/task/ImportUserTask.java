package com.ssm.task;

import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

public class ImportUserTask implements Callable<User> {
    private UserMapper userMapper;
    private User user;

    public ImportUserTask(UserMapper userMapper , User user) {
        this.userMapper = userMapper;
        this.user = user;
    }

    @Override
    public User call() throws Exception {
        userMapper.saveUser(this.user);
        return user;
    }
}
