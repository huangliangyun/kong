package com.javahly.springbootkongjwt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/12/7
 * @QQ :1136513099
 * @desc :
 */
public class MemberController {

    @RequestMapping(value = "/member/index", method = RequestMethod.GET)
    public String index(){
        return "会员系统";
    }
}
