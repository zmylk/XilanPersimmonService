package com.xilan.bengin.controller;


import com.xilan.bengin.pojo.User;
import com.xilan.bengin.service.LoginService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import com.xilan.common.vo.PageResult;
import com.xilan.common.vo.R;
import com.xilan.common.vo.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
