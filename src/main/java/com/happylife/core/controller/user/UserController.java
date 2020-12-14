package com.happylife.core.controller.user;

import com.happylife.core.common.Response;
import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.exception.EntityNotFoundException;
import com.happylife.core.exception.user.UserFilterParameterException;
import com.happylife.core.exception.uuid.UUIDException;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;


@RestController
@RequestMapping("/tuoke-web/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    public ResponseEntity<Object> test(@RequestParam(value = "name", required = true) String name){
        logger.info(messageSource.getMessage("subject", new Object[]{"juan"}, Locale.getDefault()));
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getUsersByFilter(@RequestParam(value = "userIds", required = false, defaultValue = "") String userIds,
                                                   @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                   @RequestParam(value = "sex", required = false, defaultValue = "") String sex,
                                                   @RequestParam(value = "sortby", required = false, defaultValue = "") String sortby,
                                                   @RequestParam(value = "order", required = false, defaultValue = "") String order) throws UserFilterParameterException, UUIDException {
        UserFilter userFilter = new UserFilter();
        userFilter.setUserIds(userIds);
        userFilter.setName(name);
        userFilter.setSex(sex);
        userFilter.setSortby(sortby);
        userFilter.setOrder(order);
        userFilter.validate();
        logger.info(userFilter.toString());
        List<User> users = userService.getUsersByFilter(userFilter);
        logger.info(this.messageSource.getMessage("user.filter", new Object[]{userFilter.toString()}, Locale.getDefault()));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId) throws UUIDException {
        uuidGenerator.validate(userId, "userId", "User");
        UUID uuid = uuidGenerator.getUUID(userId);;
        User user = userService.getUserById(uuid);
        logger.info(this.messageSource.getMessage("user.filter.id", new Object[]{userId}, Locale.getDefault()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUsersByIds(@RequestParam(value = "userIds", required = true) String userIds) throws UUIDException {
        //to-do: validation for userIds
        List<Object> uuids = uuidGenerator.getUUIDs(userIds);
        userService.deleteUsersByIds(uuids);
        logger.info(this.messageSource.getMessage("user.delete.ids", new Object[]{userIds}, Locale.getDefault()));
        return new ResponseEntity<>(this.messageSource.getMessage("user.delete.ids", new Object[]{userIds}, Locale.getDefault()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") String userId) throws UUIDException {
        uuidGenerator.validate(userId, "userId", "User");
        UUID uuid = uuidGenerator.getUUID(userId);
        int res = userService.deleteUserById(uuid);
        if(res == 0){
            logger.info(this.messageSource.getMessage("user.delete.res", new Object[]{res}, Locale.getDefault()));
        }else{
            logger.info(this.messageSource.getMessage("user.delete.ids", new Object[]{userId}, Locale.getDefault()));
        }
        Response response = Response.success();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        UUID uuid = uuidGenerator.getUUID();
        user.setUserId(uuid);
        int res = userService.createUser(user);
        logger.info(this.messageSource.getMessage("user.create.res", new Object[]{res}, Locale.getDefault()));
        Response response = Response.success(user);
        logger.info(this.messageSource.getMessage("user.create", new Object[]{uuid.toString()}, Locale.getDefault()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private User checkUser(UUID uuid) throws EntityNotFoundException {
        User user = userService.getUserById(uuid);
        if(user == null)
            throw new EntityNotFoundException(this.messageSource.getMessage("entity.notfound", new Object[]{uuid.toString(), "user"}, Locale.getDefault()));
        return user;
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user) throws EntityNotFoundException, UUIDException {
        uuidGenerator.validate(user.getUserId() == null? null : user.getUserId().toString(), "userId", "User");
        UUID uuid = uuidGenerator.getUUID();
        user.setUserId(uuid);
        checkUser(uuid);
        int res = userService.updateUser(user);
        logger.info(this.messageSource.getMessage("user.update.res", new Object[]{res}, Locale.getDefault()));
        Response response = Response.success(user);
        logger.info(this.messageSource.getMessage("user.update", new Object[]{uuid.toString()}, Locale.getDefault()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
