package com.app.course.security.jwt;


import com.app.course.security.user.UserSecurity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;


// mã hóa thông tin user thành chuỗi jwt
@Component
@Slf4j
public class JwtTokenProvider {

    // key bí bật
    private final String JWT_SECRET = "123456";
    // thời gian hiệu lực của jwt
    private final long JWT_EXPIRATION = 604800000L;

    private Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);


    // tạo jwt
    public String generateToken(UserSecurity userDetail) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        try {
            return JWT.create()
                    .withSubject(userDetail.getUsername())
                    .withExpiresAt(expiryDate)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // lấy thông tin từ jwt
    public String getUserNameFromJWT(String token) {

        // Tạo biến DecodedJWT để lưu trữ JWT token được cung cấp
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

        return decodedJWT.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            DecodedJWT decodedJWT = JWT.decode(authToken);
            // kiểm tra thuật toán jwt có khớp với thuật toán đang sử dụng không
            if(!decodedJWT.getAlgorithm().equals(algorithm.getName())){
                return false;
            }
            // kiểm tra jwt còn hạn hay không
            if(decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }

            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }
}
