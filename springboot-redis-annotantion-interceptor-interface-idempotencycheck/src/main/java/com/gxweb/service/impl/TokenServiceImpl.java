package com.gxweb.service.impl;

import com.gxweb.common.Constant;
import com.gxweb.common.ResponseCode;
import com.gxweb.common.ServerResponse;
import com.gxweb.exception.ServiceException;
import com.gxweb.service.TokenService;
import com.gxweb.util.JedisUtil;
import com.gxweb.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/26 18:48
 * @description ：
 * @version: 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public ServerResponse createToken() {
        String str = RandomUtil.UUID32(); //生成32为随机字符
        StrBuilder token = new StrBuilder();
        //token:
        token.append(Constant.Redis.TOKEN_PREFIX).append(str); // token: adsgaasdgas...  32位随机字符串

        //存入redis    ker  token:                                             60s
        jedisUtil.set(TOKEN_NAME, str, Constant.Redis.EXPIRE_TIME_MINUTE);

        return ServerResponse.success(token.toString());
    }

    /**
     * 检查令牌token
     *
     * @param request
     */
    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME); //头信息获取token
        System.out.println(token);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        String rtoken = jedisUtil.get(TOKEN_NAME);

        if (StringUtils.isEmpty(token)) {
            throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
        } else if (StringUtils.isEmpty(rtoken)) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        if (token.equals(rtoken)) {
            Long del = jedisUtil.del(TOKEN_NAME);
            if (del <= 0) {
                throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
            }
        }

    }
}
