package com.picture.picture_backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.picture.picture_backend.model.dto.UserQueryRequest;
import com.picture.picture_backend.model.entity.User;
import com.picture.picture_backend.model.vo.LoginUserVO;
import com.picture.picture_backend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏后用户信息
     * @param user 用户信息
     * @return 脱敏后用户信息
     */
    UserVO getUserVO(User user);


    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 查询用户
     * @param userQueryRequest 查询参数
     * @return 返回参数
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    //密码加密
    String getEncryptPassword(String userPassword);
}
