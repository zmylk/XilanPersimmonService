package com.xilan.bengin.controller;


import com.xilan.bengin.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/23 21:57
 */
@RestController
public class WXLoginController {

    @Autowired
    LoginService loginService;
    @PostMapping("wx/login")
    @ResponseBody
    public void wxLogin(@RequestParam(value = "code", required = false) String code,
                        @RequestParam(value = "rawData", required = false) String rawData,
                        @RequestParam(value = "signature", required = false) String signature,
                        @RequestParam(value = "encrypteData", required = false) String encrypteData,
                        @RequestParam(value = "iv", required = false) String iv) {
        loginService.addUser(code,rawData,signature);

    }
}
