package com.ssm.mapper;

import com.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 查询用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 保存用户
     * @param user
     * @return
     */
    int saveUser(@Param("user") User user);
}
