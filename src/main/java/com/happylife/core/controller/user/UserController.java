package com.happylife.core.controller.user;

import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tuoke-web/api/users")
public class UserController {
    @Autowired
    private UserService userService;

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
        List<User> users = userService.getUsersByFilter(userFilter);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId){
        User user = userService.selectByPrimaryKey(UUID.fromString(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUsersByIds(@RequestParam(value = "userIds", required = true) String userIds){
        return new ResponseEntity<>(String.format("Users %s are deleted", userIds), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") String userId){
        UUID uuid = UUID.fromString(userId);
        userService.deleteUserById(uuid);
        return new ResponseEntity<>(String.format("User %s is deleted", userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        UUID uuid = UUIDGenerator.getUUID();
        user.setUserId(uuid);
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        UUID uuid = UUID.fromString((String)user.getUserId());
        user.setUserId(uuid);
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
