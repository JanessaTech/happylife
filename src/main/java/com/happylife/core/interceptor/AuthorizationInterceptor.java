package com.happylife.core.interceptor;

import com.happylife.core.annotation.Authorization;
import com.happylife.core.common.Constants;
import com.happylife.core.common.token.TokenManager;
import com.happylife.core.common.token.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenManager tokenManager;

    private final static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle in AuthorizationInterceptor");
        if(! (handler instanceof HandlerMethod))
            return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        TokenModel tokenModel = tokenManager.queryToken(authorization);
        if(tokenModel != null){
            /**
             * put CURRENT_USER_NAME in request which will be used by CurrentUserMethodArgumentResolver
             * to initialize  variable "user" in LoginController.logout method
             */
            request.setAttribute(Constants.CURRENT_USER_NAME, tokenModel.getName());
            tokenManager.updateTokenTTL(tokenModel);
            return true;
        }
        if(method.getAnnotation(Authorization.class) != null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //logger.info("preHandle in AuthorizationInterceptor");
    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {
        //logger.info("afterCompletion in AuthorizationInterceptor");
    }
}
