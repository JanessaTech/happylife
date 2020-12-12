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

        logger.debug("start getUsersByFilter");
        UserFilter userFilter = new UserFilter();
        userFilter.setUserIds(userIds);
        userFilter.setName(name);
        userFilter.setSex(sex);
        userFilter.setSortby(sortby);
        userFilter.setOrder(order);
        logger.info(userFilter.toString());
        List<User> users = userService.getUsersByFilter(userFilter);
        logger.debug("end getUsersByFilter");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId){
        logger.debug("start getUserById");
        User user = userService.selectByPrimaryKey(UUID.fromString(userId));
        logger.debug("end getUserById");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUsersByIds(@RequestParam(value = "userIds", required = true) String userIds){
        logger.debug("start deleteUsersByIds");
        List<UUID> uuids = UUIDGenerator.getUUIDs(userIds);
        userService.deleteUsersByIds(uuids);
        logger.debug("end deleteUsersByIds");
        return new ResponseEntity<>(String.format("Users %s are deleted", userIds), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") String userId){
        logger.debug("start deleteUserById");
        UUID uuid = UUIDGenerator.getUUID(userId);
        userService.deleteUserById(uuid);
        logger.debug("end deleteUserById");
        return new ResponseEntity<>(String.format("User %s is deleted", userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        logger.debug("start createUser");
        UUID uuid = UUIDGenerator.getUUID();
        user.setUserId(uuid);
        userService.createUser(user);
        logger.debug("end createUser");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        logger.debug("start updateUser");
        UUID uuid = UUIDGenerator.getUUID((String)user.getUserId());
        user.setUserId(uuid);
        userService.updateUser(user);
        logger.debug("end updateUser");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
