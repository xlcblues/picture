package com.picture.picture_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.picture.picture_backend.model.entity.User;

/**
* @author blues
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-03-21 14:39:46
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
