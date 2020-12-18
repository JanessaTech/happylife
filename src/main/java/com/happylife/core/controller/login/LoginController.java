package com.happylife.core.controller.login;

import com.happylife.core.annotation.Authorization;
import com.happylife.core.annotation.CurrentUser;
import com.happylife.core.common.Response;
import com.happylife.core.common.token.TokenManager;
import com.happylife.core.common.token.TokenModel;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.exception.LoginException;
import com.happylife.core.exception.TokenException;
import com.happylife.core.exception.user.UserException;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Api(tags = {"LoginController"})
@RestController
@RequestMapping("/tuoke-web/api/tokens")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @ApiOperation("user login")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestParam(value = "name", required = true)
                                        @ApiParam("username") String name,
                                        @RequestParam(value = "password", required = true)
                                        @ApiParam("user's password") String password) throws UserException, LoginException {
        UserFilter userFilter = new UserFilter(messageSource);
        userFilter.setName(name);
        userFilter.setPassword(password);
        List<User> users = userService.getUsersByFilter(userFilter);
        if(users == null || users.size() == 0){
            throw new LoginException(this.messageSource.getMessage("login.fail", new Object[]{}, Locale.getDefault()));
        }
        TokenModel tokenModel = null;
        try {
            tokenModel = tokenManager.createToken(name);
        }catch (TokenException ex){
            throw new LoginException(ex.getMessage(), ex);
        }
        logger.info(this.messageSource.getMessage("login.success", new Object[]{name}, Locale.getDefault()));
        Response  response = Response.success(tokenModel);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    /**
     *
     * @param user initialized by {@link com.happylife.core.component.CurrentUserMethodArgumentResolver}
     * @return
     * @throws LoginException
     */
    @ApiOperation("user logout")
    @DeleteMapping
    @Authorization
    public ResponseEntity<Object> logout(@CurrentUser User user) throws LoginException{

        logger.info(user.toString());
        try{
            tokenManager.deleteToken(user.getName());
        }catch (TokenException ex){
            throw new LoginException(ex.getMessage(), ex);
        }
        String msg = this.messageSource.getMessage("logout.success", new Object[]{user.getName()}, Locale.getDefault());
        logger.info(msg);
        return new ResponseEntity<Object>(msg, HttpStatus.OK);
    }

    @GetMapping(value = "/test1")
    @Authorization
    public ResponseEntity<Object> accessWithAuthorization() throws LoginException{
        return new ResponseEntity<Object>("accessWithAuthorization", HttpStatus.OK);
    }
    @GetMapping(value = "/test2")
    public ResponseEntity<Object> accessWithOutAuthorization() throws LoginException{
        return new ResponseEntity<Object>("accessWithOutAuthorization", HttpStatus.OK);
    }
}
