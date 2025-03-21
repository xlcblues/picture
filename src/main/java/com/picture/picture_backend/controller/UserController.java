package com.picture.picture_backend.controller;

import com.picture.picture_backend.common.BaseResponse;
import com.picture.picture_backend.common.ResultUtils;
import com.picture.picture_backend.exception.ErrorCode;
import com.picture.picture_backend.exception.ThrowUtils;
import com.picture.picture_backend.model.dto.UserLoginRequest;
import com.picture.picture_backend.model.dto.UserRegisterRequest;
import com.picture.picture_backend.model.entity.User;
import com.picture.picture_backend.model.vo.LoginUserVO;
import com.picture.picture_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        Long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        LoginUserVO result = userService.userLogin(userAccount, userPassword, httpServletRequest);
        return ResultUtils.success(result);
    }

    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest httpServletRequest) {
        User user = userService.getLoginUser(httpServletRequest);
        return ResultUtils.success(userService.getLoginUserVO(user));
    }
}
