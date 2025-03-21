package com.picture.picture_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.picture.picture_backend.model.entity.User;
import com.picture.picture_backend.service.UserService;
import com.picture.picture_backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author blues
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-03-21 14:39:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}




