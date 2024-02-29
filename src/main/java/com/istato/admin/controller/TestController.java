package com.istato.admin.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.istato.admin.baseclasses.Constants.ADMIN_SERVICE_UP_AND_RUNNING;
import static com.istato.admin.baseclasses.EndPointReffer.TEST_ADMIN;

@RestController
@Slf4j
public class TestController {

    @GetMapping(TEST_ADMIN)
    public String testAdmin(){
        return ADMIN_SERVICE_UP_AND_RUNNING;
    }

}
