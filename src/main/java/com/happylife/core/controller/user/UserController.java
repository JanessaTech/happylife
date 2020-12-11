package com.happylife.core.controller.user;

import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
