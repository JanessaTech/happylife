package com.happylife.core.controller.idem;

import com.happylife.core.common.Response;
import com.happylife.core.exception.IdempotentException;
import com.happylife.core.service.IdempotentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/tuoke-web/api/idem")
public class IdempotentController {
    @Autowired
    private IdempotentService idempotentService;

    @PostMapping
    public ResponseEntity<Object> createToken() throws IdempotentException{
        String token = idempotentService.createToken();
        return new ResponseEntity<Object>(Response.success(token), HttpStatus.OK);
    }
}
