package com.happylife.core.controller.user;

import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.dto.user.UserFilter;
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
                                                   @RequestParam(value = "order", required = false, defaultValue = "") String order) {
        UserFilter userFilter = new UserFilter();
        userFilter.setUserIds(userIds);
        userFilter.setName(name);
        userFilter.setSex(sex);
        userFilter.setSortby(sortby);
        userFilter.setOrder(order);
        logger.info(userFilter.toString());
        List<User> users = userService.getUsersByFilter(userFilter);
        logger.debug(this.messageSource.getMessage("user.filter", new Object[]{userFilter.toString()}, Locale.getDefault()));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId){
        User user = userService.selectByPrimaryKey(UUID.fromString(userId));
        logger.debug(this.messageSource.getMessage("user.filter.id", new Object[]{userId}, Locale.getDefault()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUsersByIds(@RequestParam(value = "userIds", required = true) String userIds){
        List<Object> uuids = UUIDGenerator.getUUIDs(userIds);
        userService.deleteUsersByIds(uuids);
        logger.debug(this.messageSource.getMessage("user.delete.ids", new Object[]{userIds}, Locale.getDefault()));
        return new ResponseEntity<>(String.format("Users %s are deleted", userIds), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") String userId){
        UUID uuid = UUIDGenerator.getUUID(userId);
        userService.deleteUserById(uuid);
        logger.debug(this.messageSource.getMessage("user.delete.ids", new Object[]{userId}, Locale.getDefault()));
        return new ResponseEntity<>(String.format("User %s is deleted", userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        UUID uuid = UUIDGenerator.getUUID();
        user.setUserId(uuid);
        userService.createUser(user);
        logger.debug(this.messageSource.getMessage("user.create", new Object[]{uuid.toString()}, Locale.getDefault()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        UUID uuid = UUIDGenerator.getUUID((String)user.getUserId());
        user.setUserId(uuid);
        userService.updateUser(user);
        logger.debug(this.messageSource.getMessage("user.update", new Object[]{uuid.toString()}, Locale.getDefault()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
