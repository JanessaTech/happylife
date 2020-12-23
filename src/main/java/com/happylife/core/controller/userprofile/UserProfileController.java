package com.happylife.core.controller.userprofile;

import com.github.pagehelper.PageInfo;
import com.happylife.core.annotation.IdAllowed;
import com.happylife.core.common.Response;
import com.happylife.core.component.UUIDGenerator;
import com.happylife.core.common.token.TokenManager;
import com.happylife.core.dto.token.Token;
import com.happylife.core.dto.user.UserProfileFilter;
import com.happylife.core.exception.EntityNotFoundException;
import com.happylife.core.exception.TokenException;
import com.happylife.core.exception.login.LoginAuth2Exception;
import com.happylife.core.exception.user.UserProfileException;
import com.happylife.core.exception.user.UserFilterParameterException;
import com.happylife.core.exception.uuid.UUIDException;
import com.happylife.core.mbg.model.Student;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Api("UserProfileController")
@Validated
@RestController
@RequestMapping("/tuoke-web/api/auth2/users")
public class UserProfileController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("auth2TokenManager")
    private TokenManager auth2TokenManager;


    /**
     * This is a demo to show how to use @Valid and
     * However, the method studentVerify doesn't provide good solution to collect exceptions/errors thrown by @Valid
     * If we do nothing about collecting exceptions/errors, spring will throw exceptions/errors in backend(you will see it on spring console/log file)
     * Currently, there are several ways to collect exceptions/errors
     *   1. Add anther parameter which type is BindingResult in studentVerify, then use AOP or directly return errors within studentVerify.
     *      For AOP, see details: http://www.macrozheng.com/#/technology/springboot_validator
     *      For direct return, see details: https://blog.csdn.net/sunnyzyq/article/details/103527380
     *   2. Use annotation @ExceptionHandler on the controller-specific level or global level.
     *      See details at handleValidationExceptions method (controller-specific level) or at {@link com.happylife.core.exception.GlobalExceptionHandler}(global level)
     * @param student
     * @return
     */
    @PostMapping(value = "/student")
    public ResponseEntity<Object> studentVerify(@Valid @RequestBody Student student){
        return new ResponseEntity<>("student is valid", HttpStatus.OK);
    }

    /**
     * we could handle controller-specific exceptions by using the method below.
     * For the common exceptions, put the handling logic at here. See {@link com.happylife.core.exception.GlobalExceptionHandler}
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ApiOperation("user login")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestParam(value = "name", required = true)
                                            @Length(min = 5, max = 10, message = "the length of name is within 5 and 10")
                                            @ApiParam("username") String name,
                                        @RequestParam(value = "password", required = true)
                                            @Length(min = 3, max = 10, message = "the length of password is within 3 and 10")
                                            @ApiParam("user's password") String password) throws LoginAuth2Exception, TokenException {
        Token token = null;
        try {
            token  = auth2TokenManager.createToken(name, password);
        } catch (TokenException e) {
            e.printStackTrace();
            throw new LoginAuth2Exception(e.getMessage(),e);
        }
        Token  oldToken = auth2TokenManager.queryToken(token.getAccess_token());
        if(oldToken != null){
            // user has logined before
            auth2TokenManager.deleteToken(oldToken.getAccess_token());
            token = auth2TokenManager.refreshToken(token);
        }
        auth2TokenManager.addToken(token);
        return new ResponseEntity<Object>(token, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            errors.put(error.getPropertyPath().toString(), error.getMessage());
        });
        return errors;
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Object> logout(@RequestParam(value = "name", required = true) String name){
        return null;
    }


    @ApiOperation("getUsersByFilter")
    @GetMapping
    public ResponseEntity<Object> getUserProfilesByFilter(@RequestParam(value = "userIds", required = false, defaultValue = "") String userIds,
                                                          @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                          @RequestParam(value = "sex", required = false, defaultValue = "") String sex,
                                                          @RequestParam(value = "sortby", required = false, defaultValue = "") String sortby,
                                                          @RequestParam(value = "order", required = false, defaultValue = "") String order,
                                                          @RequestParam(value = "page", required = true) @Min(1) int page,
                                                          @RequestParam(value = "pageSize", required = true) @Min(2) int pageSize,
                                                          @RequestParam(value = "access_token", required = true) String access_token) throws UserFilterParameterException, UserProfileException {
        UserProfileFilter userProfileFilter = new UserProfileFilter(this.messageSource);
        userProfileFilter.setUserIds(userIds);
        userProfileFilter.setName(name);
        userProfileFilter.setSex(sex);
        userProfileFilter.setSortby(sortby);
        userProfileFilter.setOrder(order);
        userProfileFilter.validate();  // to-do: no need validation
        logger.info(userProfileFilter.toString());
        PageInfo<User> pageInfo = null;
        try{
            pageInfo = userService.getUsersByFilter(userProfileFilter, page, pageSize);
        }catch(UserProfileException ex){
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.info(this.messageSource.getMessage("user.filter", new Object[]{userProfileFilter.toString()}, Locale.getDefault()));
        Response response = Response.success(pageInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId")
                                                  @NotNull
                                                  @NotBlank(message = "userId cannot be blank")
                                                  @IdAllowed(message = "userId is not valid UUID") String userId,
                                              @RequestParam(value = "access_token", required = true) String access_token) throws EntityNotFoundException, UserProfileException {
        UUID uuid = uuidGenerator.getUUID(userId);
        User user = null;
        try{
            user = userService.getUserById(uuid);
        }catch(UserProfileException ex){
            logger.error(ex.getMessage(), ex);
            throw ex;
        }

        if(user == null){
            throw new EntityNotFoundException(this.messageSource.getMessage("entity.notfound", new Object[]{uuid.toString(), "user"}, Locale.getDefault()));
        }
        logger.info(this.messageSource.getMessage("user.filter.id", new Object[]{userId}, Locale.getDefault()));
        Response response = Response.success(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUsersByIds(@RequestParam(value = "userIds", required = true) String userIds,
                                                   @RequestParam(value = "access_token", required = true) String access_token) throws UUIDException, UserProfileException {
        //to-do: validation for userIds
        List<Object> uuids = uuidGenerator.getUUIDs(userIds);
        try{
            int res = userService.deleteUsersByIds(uuids);
            logger.info(this.messageSource.getMessage("user.delete.res", new Object[]{res}, Locale.getDefault()));
            logger.info(this.messageSource.getMessage("user.delete.ids", new Object[]{userIds}, Locale.getDefault()));
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new UserProfileException(ex.getMessage());
        }

        Response response = Response.success();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") String userId,
                                                 @RequestParam(value = "access_token", required = true) String access_token) throws UUIDException, UserProfileException {
        uuidGenerator.validate(userId, "userId", "User");
        UUID uuid = uuidGenerator.getUUID(userId);
        try{
            int res = userService.deleteUserById(uuid);
            logger.info(this.messageSource.getMessage("user.delete.res", new Object[]{res}, Locale.getDefault()));
            logger.info(this.messageSource.getMessage("user.delete.ids", new Object[]{userId}, Locale.getDefault()));
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex); // print exception stack
            throw new UserProfileException(ex.getMessage());
        }

        Response response = Response.success();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user,
                                             @RequestParam(value = "access_token", required = true) String access_token) throws UserProfileException {
        UUID uuid = uuidGenerator.getUUID();
        user.setUserId(uuid);
        try{
            int res = userService.createUser(user);
            logger.info(this.messageSource.getMessage("user.create.res", new Object[]{res}, Locale.getDefault()));
        }catch(UserProfileException ex){
            logger.error(ex.getMessage(), ex);
            throw ex;
        }

        Response response = Response.success(user);
        logger.info(this.messageSource.getMessage("user.create", new Object[]{uuid.toString()}, Locale.getDefault()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private User checkUser(UUID uuid) throws EntityNotFoundException, UserProfileException {
        User user = userService.getUserById(uuid);
        if(user == null)
            throw new EntityNotFoundException(this.messageSource.getMessage("entity.notfound", new Object[]{uuid.toString(), "user"}, Locale.getDefault()));
        return user;
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user,
                                             @RequestParam(value = "access_token", required = true) String access_token) throws EntityNotFoundException, UUIDException, UserProfileException {
        uuidGenerator.validate(user.getUserId() == null? null : user.getUserId().toString(), "userId", "User");
        UUID uuid = uuidGenerator.getUUID(user.getUserId().toString());
        user.setUserId(uuid);
        checkUser(uuid);
        try{
            int res = userService.updateUser(user);
            logger.info(this.messageSource.getMessage("user.update.res", new Object[]{res}, Locale.getDefault()));
        }catch(UserProfileException ex){
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        Response response = Response.success(user);
        logger.info(this.messageSource.getMessage("user.update", new Object[]{uuid.toString()}, Locale.getDefault()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
