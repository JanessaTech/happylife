package com.happylife.core.controller.user;

import com.happylife.core.dto.user.UserDTO;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tuoke-web/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public ResponseEntity<Object> selectByPrimaryKey(
            @RequestParam(value = "userId", required = true, defaultValue = "") String userId){
        User user = userService.selectByPrimaryKey(UUID.fromString(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/selectUserByFilter", method = RequestMethod.GET)
    public ResponseEntity<Object> selectByFilter(@RequestBody UserFilter userFilter){
        return null;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO){
        return null;
        //return new ResponseEntity(null, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUser(@RequestParam(value = "userId", required = true, defaultValue = "_") String userId){
        return null;
    }





}
