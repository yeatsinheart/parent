package com.base.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {
    //签名私钥
    private static final String key = "test";
    //签名有效时间
    private static final long ttl = 1000;

    public static String createJwtToken(String userId, String name, Map<String, Object> map) {
        //设置失效时间
        //获取当前时间
        long now = System.currentTimeMillis();
        //当前时间+有效时间=过期时间
        long exp = now + ttl;
        //创建JwtBuilder

        JwtBuilder jwtBuilder = Jwts.builder().setId(userId).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);

        //根据map设置clamis
        jwtBuilder.setClaims(map);
        //设置失效时间
        jwtBuilder.setExpiration(new Date(exp));
        String token = jwtBuilder.compact();
        return token;
    }

    public static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            log.error("解密token出错{}", e);
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", 101);
        map.put("companyName", "兰博");
        String token = createJwtToken("99", "测试", map);
        System.out.println(token);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Claims claims = parseToken(token);
        if (null == claims) {
            System.out.println("没有正确解析");
        }
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
        //解析自定义的claim中的内容
        Integer companyId = (Integer) claims.get("companyId");
        String companyName = (String) claims.get("companyName");
        System.out.println(companyId);
        System.out.println(companyName);

    }

}
