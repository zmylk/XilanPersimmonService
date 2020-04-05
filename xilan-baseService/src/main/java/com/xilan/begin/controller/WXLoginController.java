package com.xilan.begin.controller;


import com.xilan.begin.pojo.User;
import com.xilan.begin.service.LoginService;
import com.xilan.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

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
    public R wxLogin(@RequestParam(value = "code", required = false) String code,
                                @RequestParam(value = "rawData", required = false) String rawData,
                                @RequestParam(value = "signature", required = false) String signature,
                                @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                @RequestParam(value = "iv", required = false) String iv) {
        User userRes = loginService.addUser(code, rawData, signature);
        String openId = userRes.getOpenId();
        HashMap<String, String> map = new HashMap<>();
        map.put("openid", openId);
        map.put("status","200");
        return R.ok(map);
    }

}
