package com.twocats.treehole.common.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author frankfyang
 * @date 2023-08-15 19:28
 */
@Component
public class JWTUtil {

    private static String secretKey;
    private static String expireTime;
    private static final String UID_CLAIM = "uid";

    /**
     * token秘钥，请勿泄露，请勿随便修改
     */
    @Value("${treehole.jwt.secret}")
    public void setSecretKey(String secret) {
        JWTUtil.secretKey = secret;
    }

    /**
     * 过期时间
     */
    @Value("${treehole.jwt.expireTime}")
    private void setExpireTime(String expireTime) {
        JWTUtil.expireTime = expireTime;
    }


    /**
     * 创建token
     *
     * @param uid uid
     * @return
     */
    public static String createToken(Long uid) {
        JwtBuilder builder = Jwts.builder();
        return builder.claim(UID_CLAIM, uid)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
