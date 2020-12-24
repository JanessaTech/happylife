package com.happylife.core.interceptor;

import com.happylife.core.annotation.AutoIdempotent;
import com.happylife.core.common.Constants;
import com.happylife.core.exception.IdempotentException;
import com.happylife.core.service.IdempotentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Locale;

@Component
public class IdempotentInterceptor extends AbstractInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(IdempotentInterceptor.class);

    @Autowired
    private IdempotentService idempotentService;

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle in IdempotentInterceptor");
        if(! (handler instanceof HandlerMethod))
            return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(AutoIdempotent.class) != null){
            String idem_token = request.getHeader(Constants.IDEMPOTENT_REQUEST_HEADER);
            if(idem_token == null || idem_token.trim().equals("")){
                throw new IdempotentException(this.messageSource.getMessage("idem.token.notfound.request", new Object[]{}, Locale.getDefault()));
            }
            if(idempotentService.isExist(idem_token)){
                idempotentService.remove(idem_token);
                logger.info(this.messageSource.getMessage("idem.token.remove.success", new Object[]{idem_token}, Locale.getDefault()));
            }else{
                String msg = this.messageSource.getMessage("idem.token.notfound.redis", new Object[]{idem_token}, Locale.getDefault());
                logger.info(msg);
                throw new IdempotentException(msg);
            }
        }
        return true;
    }
}
