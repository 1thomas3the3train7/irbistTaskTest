package com.example.irbisBlock.jwt;

import com.example.irbisBlock.dto.RoleDto;
import com.example.irbisBlock.dto.UserDto;
import com.example.irbisBlock.exception.IncorrectJwtTokenException;
import com.example.irbisBlock.exception.NotValidRequestException;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.example.irbisBlock.dto.dtoTypeToken.roleDtoList;


@Service
public class JwtUtils {
    private final SecretKey jwtAccessToken;
    private final SecretKey jwtRefreshToken;
    private final Gson gson = new Gson();

    public JwtUtils(@Value("${jwt.accessToken}")String jwtAccessToken, @Value("${jwt.refreshToken}") String jwtRefreshToken) {
        this.jwtAccessToken = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessToken));
        this.jwtRefreshToken = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshToken));
    }
    public String generateAccessToken(final UserDto userDTO){
        final LocalDateTime time = LocalDateTime.now();
        final Instant accessInst = time.plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessTime = Date.from(accessInst);
        return Jwts.builder()
                .setSubject(userDTO.getEmail())
                .setExpiration(accessTime)
                .claim("roles",gson.toJson(userDTO.getRoles()))
                .claim("type","access")
                .signWith(jwtAccessToken)
                .compact();
    }
    public String generateRefreshToken(final UserDto userDTO){
        final LocalDateTime time = LocalDateTime.now();
        final Instant refreshInst = time.plusDays(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshTime = Date.from(refreshInst);
        return Jwts.builder()
                .setSubject(userDTO.getEmail())
                .setExpiration(refreshTime)
                .claim("roles",gson.toJson(userDTO.getRoles()))
                .claim("type","refresh")
                .signWith(jwtRefreshToken)
                .compact();
    }
    public boolean validateAccessToken(final String accessToken){
        try {
            final Claims claims = getAccessClaims(accessToken);
            if(claims == null){
                return false;
            }
            if(!((String) claims.get("type")).equals("access")){
                return false;
            }
            return validateToken(accessToken,jwtAccessToken);
        } catch (NullPointerException | ExpiredJwtException n){
            System.out.println(n.getMessage());
            return false;
        }
    }
    public boolean validateRefreshToken(final String refreshToken){
        final Claims claims = getAccessClaims(refreshToken);
        if(!((String) claims.get("type")).equals("refresh")){
            throw new IncorrectJwtTokenException("Incorrect token type");
        }
        return validateToken(refreshToken,jwtRefreshToken);
    }
    private boolean validateToken(final String typeToken,final Key key){
        try {
            if(typeToken == null || typeToken.equals("")){
                System.out.println("falsee");
                return false;
            }
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(typeToken);
            return true;
        } catch (ExpiredJwtException expEx) {
            System.out.println("token expired");
        } catch (UnsupportedJwtException unsEx) {
            System.out.println(unsEx.getMessage());
        } catch (MalformedJwtException mjEx) {
            System.out.println(mjEx.getMessage());
        }
        return false;
    }
    public Claims getAccessClaims(final String token) {
        return getClaims(token, jwtAccessToken);
    }

    public Claims getRefreshClaims(final String token) {
        return getClaims(token, jwtRefreshToken);
    }

    private Claims getClaims(final String token, final Key secret) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException | SignatureException m){
            return null;
        }
    }
    public JwtAuth generate(final Claims claims){
        final JwtAuth jwtAuth = new JwtAuth();
        jwtAuth.setRoles(getRoles(claims));
        jwtAuth.setEmail(claims.getSubject());
        return jwtAuth;
    }
    private List<RoleDto> getRoles(final Claims claims){
        try {
            final List<RoleDto> roles = gson.fromJson((String) claims.get("roles"), roleDtoList);
            return roles;
        } catch (NullPointerException n){
            throw new NotValidRequestException("Not valid request (null roles)");
        }
    }
    public UserDto parseRefreshToken(final String refresh){
        final Claims claims = getRefreshClaims(refresh);
        return new UserDto(claims.getSubject(), getRoles(claims));
    }

}
