package com.happylife.core.component;

import com.happylife.core.annotation.CurrentUser;
import com.happylife.core.common.Constants;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.getParameterType().isAssignableFrom(User.class) &&
                methodParameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String userName = (String)nativeWebRequest.getAttribute(Constants.CURRENT_USER_NAME, RequestAttributes.SCOPE_REQUEST);
        if(userName != null){
            UserFilter userFilter = new UserFilter(messageSource);
            userFilter.setName(userName);
            List<User> user = userService.getUsersByFilter(userFilter);
            return user.get(0);
        }
        return new MissingServletRequestPartException(Constants.CURRENT_USER_NAME);
    }
}
