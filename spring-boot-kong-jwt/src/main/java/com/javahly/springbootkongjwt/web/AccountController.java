package com.javahly.springbootkongjwt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/11/21
 * @QQ :1136513099
 * @desc :
 */

public class AccountController {


    @RequestMapping(value = "/account/index", method = RequestMethod.GET)
    public String index() {
        return "账号系统";
    }

}
