package com.springboot.dlc.handler;


import com.springboot.dlc.entity.SysManager;
import com.springboot.dlc.exception.AuthException;
import com.springboot.dlc.redis.RedisService;
import com.springboot.dlc.result.ResultEnum;
import com.springboot.dlc.result.ResultStatus;
import com.springboot.dlc.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: liujiebang
 * @Date: Create in 2018/8/14
 * @Description: 后台授权
 **/
@Slf4j
public class BackAuthorizationInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisService redisService;

    /**
     * @Description 权限拦截
     * @Date 2018/8/20 21:35
     * @Author liangshihao
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //请求资源路径
        String requestURI = request.getRequestURI();
        SysManager manager = (SysManager) request.getAttribute(ResultStatus.MANAGER);
        //超级管理员直接放行
        if (manager != null && manager.getManagerType() == 1) {
            return true;
        }
        List<String> authorityList = (List<String>) redisService.get(ResultStatus.AUTHORITY + manager.getManagerId());
        if (authorityList.contains(requestURI)) {
            return true;
        } else {
            throw new AuthException(ResultEnum.CODE_21);
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
