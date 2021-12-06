package org.example.restAdapter.sercurity;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.restAdapter.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private static final String VALIDATED_DATE = "date";
    private static final String secretKey = "IMFjdkYY7TTy6RfewQR4PK5jtm0iO3jfldasLJLH2332NLlhhLtuufFFgiioip90322XXXoifsolaHGGkafTUTu";
    private static final int expireTime = 360000000;

    private static String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expireTime);
    }

    private static byte[] generateShareSecret() {
        return  secretKey.getBytes(StandardCharsets.UTF_8);
    }

    public  String getEmailFromToken(String token) throws BusinessException {
        String uuid = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            uuid = claims.getStringClaim("email");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(HttpStatus.UNAUTHORIZED, "Bạn không có quyền truy cập vào service");
        }
        return uuid;
    }

    public static JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        return  claims.getExpirationTime();
    }

    public Boolean validateToken(String token) throws BusinessException {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        final String email = getEmailFromToken(token);

        if (StringUtils.isBlank(email)) {
            return false;
        }
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public static String generateTokenForStudent(String email, String mobileNumber, Long id) {
        String token = null;
        try {
            // Create HMAC signer
            JWSSigner signer = new MACSigner(generateShareSecret());

            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("id", id);
            builder.claim("email", email);
            builder.claim("mobileNumber", mobileNumber);
            builder.claim(VALIDATED_DATE, formatDate(LocalDateTime.now()));
            builder.expirationTime(generateExpirationDate());

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            // Apply the HMAC protection
            signedJWT.sign(signer);

            // Serialize to compact form, produces something like
            // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
            token = signedJWT.serialize();
            log.info("Token: " + token);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
