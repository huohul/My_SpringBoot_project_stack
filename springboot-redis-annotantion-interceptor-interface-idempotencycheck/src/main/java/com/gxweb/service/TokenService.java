package com.gxweb.service;


import com.gxweb.common.ServerResponse;
import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    /**
     * 创建token
     * @return
     */
    ServerResponse createToken();

    //拦截器调用
    void checkToken(HttpServletRequest request);

}
