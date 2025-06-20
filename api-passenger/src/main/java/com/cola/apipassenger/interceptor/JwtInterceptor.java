package com.cola.apipassenger.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    // spring启动时这个bean的注入是在拦截器之后的，所以实质上仅用这个注入不可行，需要在拦截器执行之前初始化
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        boolean result = true;
//        String resultString = "";
//
//        String token = request.getHeader("Authorization");
//        // 解析token
//        TokenResult tokenResult = JwtUtils.checkToken(token);
//
//
//
//        if (tokenResult == null){
//            resultString = "access token invalid";
//            result = false;
//        } else {
//            // 拼接key
//            String phone = tokenResult.getPhone();
//            String identity = tokenResult.getIdentity();
//            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
//            // 从Redis中取出token
//            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
//            if ((StringUtils.isBlank(tokenRedis)) || (!tokenRedis.trim().equals(token.trim()))){
//                resultString = "access token invalid";
//                result = false;
//            }
//
//        }
//
//
//        if (!result){
//            PrintWriter out = response.getWriter();
//            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
//        }
//
//        return true;
//    }
}
