package com.happylife.core.controller.userprofile;

import com.happylife.core.component.UUIDGenerator;
import com.happylife.core.exception.user.UserProfileException;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserService;
import com.happylife.core.service.UserTransactionTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/tuoke-web/api/auth2/users/test")
public class UserProfileTrasanctionTestController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileTrasanctionTestController.class);

    @Autowired
    private UUIDGenerator uuidGenerator;

    @Autowired
    private UserTransactionTestService userTransactionTestService;

    @PostMapping(value = "/{casenum}")
    public ResponseEntity<Object> transaction_case1(@RequestBody List<User> users,
                                                    @PathVariable(value = "casenum") String casenum,
                                                    @RequestParam(value = "access_token", required = true) String access_token) throws UserProfileException {
        for(User user : users){
            user.setUserId(uuidGenerator.getUUID(user.getUserId().toString()));
        }
        if(casenum.equals("case1")){
            this.userTransactionTestService.case1(users);
        }else if(casenum.equals("case2")){
            this.userTransactionTestService.case2(users);
        }else if(casenum.equals("case3")){
            this.userTransactionTestService.case3(users);
        }else if(casenum.equals("case4")){
            this.userTransactionTestService.case4(users);
        }else if(casenum.equals("case5")){
            this.userTransactionTestService.case5(users);
        }else if(casenum.equals("case6")){
            this.userTransactionTestService.case6(users);
        }else if(casenum.equals("case7")){
            this.userTransactionTestService.case7(users);
        }else if(casenum.equals("case8")){
            this.userTransactionTestService.case8(users);
        }else if(casenum.equals("case9")){
            this.userTransactionTestService.case9(users);
        }else if(casenum.equals("case10")){
            this.userTransactionTestService.case10(users);
        }else if(casenum.equals("case11")){
            this.userTransactionTestService.case11(users);
        }else if(casenum.equals("case12")){
            this.userTransactionTestService.case12(users);
        }else if(casenum.equals("case13")){
            this.userTransactionTestService.case13(users);
        }else if(casenum.equals("case14")){
            this.userTransactionTestService.case14(users);
        }else if(casenum.equals("case15")){
            this.userTransactionTestService.case15(users);
        }else if(casenum.equals("case16")){
            this.userTransactionTestService.case16(users);
        }else if(casenum.equals("case17")){
            this.userTransactionTestService.case17(users);
        }else if(casenum.equals("case18")){
            this.userTransactionTestService.case18(users);
        }else if(casenum.equals("case19")){
            this.userTransactionTestService.case19(users);
        }else if(casenum.equals("case20")){
            this.userTransactionTestService.case20(users);
        }else if(casenum.equals("case21")){
            this.userTransactionTestService.case21(users);
        }else if(casenum.equals("case22")){
            this.userTransactionTestService.case22(users);
        }else if(casenum.equals("case23")){
            this.userTransactionTestService.case23(users);
        }else if(casenum.equals("case24")){
            this.userTransactionTestService.case24(users);
        }else if(casenum.equals("case25")){
            this.userTransactionTestService.case25(users);
        }else if(casenum.equals("case26")){
            this.userTransactionTestService.case26(users);
        }else if(casenum.equals("case27")){
            this.userTransactionTestService.case27(users);
        }else if(casenum.equals("case28")){
            this.userTransactionTestService.case28(users);
        }else if(casenum.equals("case29")){
            this.userTransactionTestService.case29(users);
        }else if(casenum.equals("case30")){
            this.userTransactionTestService.case30(users);
        }else if(casenum.equals("case31")){
            this.userTransactionTestService.case31(users);
        }else if(casenum.equals("case32")){
            this.userTransactionTestService.case32(users);
        }else if(casenum.equals("case33")){
            this.userTransactionTestService.case33(users);
        }else if(casenum.equals("case34")){
            this.userTransactionTestService.case34(users);
        }

        return new ResponseEntity<>(casenum + " is ok", HttpStatus.OK);
    }
}
