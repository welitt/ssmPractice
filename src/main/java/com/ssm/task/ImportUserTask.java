package com.ssm.task;

import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;

import java.util.concurrent.Callable;

public class ImportUserTask implements Callable<User> {
    private UserMapper userMapper;
    private User user;

    public ImportUserTask(User user) {
        this.userMapper = null;
        this.user = user;
    }

    @Override
    public User call() throws Exception {
        return user;
    }
}
