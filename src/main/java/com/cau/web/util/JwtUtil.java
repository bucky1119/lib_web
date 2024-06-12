package com.cau.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "my_secret_key";
    private static final long EXPIRATION_TIME = 1800000; // 30 min in milliseconds

    // 生成包含角色信息的JWT
    public static String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .compact();
    }

    // 提取JWT中的声明
    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }

    // 提取JWT中的用户名
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 提取JWT中的角色
    public static String extractRole(String token) {
        return (String) extractClaims(token).get("role");
    }

    // 检查JWT是否过期
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 验证JWT
    public static boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // 检查用户是否具有特定角色
    public static boolean hasRole(String token, String role) {
        return role.equals(extractRole(token));
    }
}
