package com.mashibing.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.utils.JwtUtils;
import javafx.print.Printer;
import net.sf.json.JSONObject;
import org.apache.catalina.startup.Catalina;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";

        String token = request.getHeader("Authorization");


        try {
            JwtUtils.parseToken(token);

        } catch (SignatureVerificationException e){
            // 签名错误异常
            resultString = "token sign error";
            result = false;
        } catch (TokenExpiredException e){
            // token过期异常
            resultString = "token time out";
            result = false;
        } catch (AlgorithmMismatchException e){
            // 算法异常
            resultString = "token AlgorithmMismatchException";
            result = false;
        }
        catch (Exception e) {
            resultString = "token invalid";
            result = false;
        }

        if (!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return true;
    }
}
