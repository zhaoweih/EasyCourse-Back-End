package com.blade.demo.route.util.oauth;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Key;

import io.jsonwebtoken.*;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.security.Security;
import java.util.Calendar;
import java.util.Date;

/**
 * Token验证类
 */
public class OAtuthUtil {

    public static String createJWT(String subject) {
        long ttlMillis = daysToMillis(ApiKey.EXPRIRE_DAYS);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encrytSHA256(ApiKey.CONTENT, ApiKey.PASSWORD));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(ApiKey.JWT_ID)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(ApiKey.ISSUERS)
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) throws ExpiredException {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(encrytSHA256(ApiKey.CONTENT, ApiKey.PASSWORD)))
                .parseClaimsJws(jwt).getBody();
        Date curTime = Calendar.getInstance().getTime();
        if (claims.getExpiration().getTime() - curTime.getTime() < 0) {
            throw new ExpiredException("token已过期");
        }
        return claims;
    }

    public static String encrytSHA256(String content, String secret) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF8"),
                    "HmacSHA256");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] digest = mac.doFinal(content.getBytes("UTF-8"));
            return new HexBinaryAdapter().marshal(digest);
        } catch (Exception e) {
            throw new RuntimeCryptoException("加密异常");
        }
    }

    /**
     * 天数转换毫秒数
     *
     * @param days
     * @return
     */
    public static long daysToMillis(long days) {
        long daysInMillis = days * 24L * 60L * 60000L;
        return daysInMillis;
    }

    //    //Sample method to construct a JWT
//    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
//
//        //The JWT signature algorithm we will be using to sign the token
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        //We will sign our JWT with our ApiKey secret
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encrytSHA256(ApiKey.CONTENT, ApiKey.PASSWORD));
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        //Let's set the JWT Claims
//        JwtBuilder builder = Jwts.builder().setId(id)
//                .setIssuedAt(now)
//                .setSubject(subject)
//                .setIssuer(issuer)
//                .signWith(signatureAlgorithm, signingKey);
//
//        //if it has been specified, let's add the expiration
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//
//        //Builds the JWT and serializes it to a compact, URL-safe string
//        return builder.compact();
//    }

}
