package com.happylife.core.interceptor;

import com.happylife.core.common.Constants;
import com.happylife.core.common.token.TokenManager;
import com.happylife.core.dto.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class Auth2Interceptor extends AbstractInterceptor{
    @Autowired
    @Qualifier("auth2TokenManager")
    private TokenManager auth2TokenManager;

    private final static Logger logger = LoggerFactory.getLogger(Auth2Interceptor.class);

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle in AuthorizationInterceptor");
        // to-do: do we need redis to store token? think about twice!
        String access_token = request.getParameter(Constants.ACCESS_TOKEN_KEY);
        if(access_token == null || access_token.trim().equals("")){
            logger.error(this.messageSource.getMessage("token.request.empty", new Object[]{}, Locale.getDefault()));
            return false;
        }
        Token token = auth2TokenManager.queryToken(access_token);
        if(token == null){
            String msg = this.messageSource.getMessage("token.redis.empty",new Object[]{access_token}, Locale.getDefault());
            response.getWriter().println(msg);
            return false;
        }
        // update token ttl in redis so that user can access apis when token exists in redis
        auth2TokenManager.updateToken(access_token);
        /*
        if(! (handler instanceof HandlerMethod))
            return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(Authorization.class) != null){
            String authorization = request.getHeader(Constants.AUTHORIZATION);
            String[] authorizationParam = parseAuthorization(authorization);
            if(authorizationParam != null){
                TokenModel tokenModel = tokenManager.queryToken(authorizationParam[0]);
                String expectedToken = authorizationParam[1];
                if(tokenModel.getToken().equals(expectedToken)){
                    request.setAttribute(Constants.CURRENT_USER_NAME, tokenModel.getName());
                    tokenManager.updateToken(tokenModel);
                    return true;
                }

            }

            response.getWriter().println(this.messageSource.getMessage("authorization.fail", new Object[]{}, Locale.getDefault()));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }*/

        /** case1 hits here*/
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
