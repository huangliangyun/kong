package com.javahly.springbootkongjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.util.Date;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/11/21
 * @QQ :1136513099
 * @desc :
 */
public class JwtUtil {

    public static String getJwt(String key, String secret) {
        String token = null;
        Date currentTime = new Date();
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer(key)
                    //如果要设置过期时间，需要在 Kong 中设置载荷exp
                    //指定过期时间，在 kong 中 可指定 maximum expiration 最大过期时间，此时间不能超过 maximum expiration，否则生成 token 无效
                    .withExpiresAt(new Date(currentTime.getTime() + 60000L))
                    .sign(algorithm);
            System.out.println(new Date());
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public static void main(String[] args) {
        System.out.println(getJwt("KA6Uf2ATwe69HndTQbz1BVCkgcq3IFwM", "pgmVPMPmF8cy66JmHqRuRA1cZNZUs9p2"));
    }
}
