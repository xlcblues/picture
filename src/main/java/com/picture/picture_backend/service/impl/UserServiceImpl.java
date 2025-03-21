package com.picture.picture_backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.picture.picture_backend.exception.BusinessException;
import com.picture.picture_backend.exception.ErrorCode;
import com.picture.picture_backend.model.entity.User;
import com.picture.picture_backend.model.enums.UserRoleEnum;
import com.picture.picture_backend.service.UserService;
import com.picture.picture_backend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author blues
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-03-21 14:39:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //校验
        if(StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }

        if(userAccount.length() <= 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }

        if(userPassword.length() <= 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度过短");
        }

        if(!StrUtil.equals(userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码不一致");
        }

        //检查是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }

        //密码加密
        String encryptPassword = getEncryptPassword(userPassword);

        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("momo");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean flag = this.save(user);
        if(!flag) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }

        return user.getId();
    }


    public String getEncryptPassword(String userPassword)
    {
        final String SALT = "blues";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }
}




