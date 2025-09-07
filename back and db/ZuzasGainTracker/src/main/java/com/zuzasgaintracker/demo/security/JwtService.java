package com.zuzasgaintracker.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final SecretKey key;
    private final long accessTokenMinutes;
    private final long refreshTokenDays;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.access-token-mins}") long accessTokenMinutes,
            @Value("${app.jwt.refresh-token-days}") long refreshTokenDays
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenMinutes = accessTokenMinutes;
        this.refreshTokenDays = refreshTokenDays;
    }

    public String generateAccessToken(UserDetails user, Map<String, Object> extraClaims) {
        return buildToken(user.getUsername(), extraClaims, Duration.ofMinutes(accessTokenMinutes), "access");
    }

    public String generateRefreshToken(UserDetails user) {
        return buildToken(user.getUsername(), Map.of(), Duration.ofDays(refreshTokenDays), "refresh");
    }

    private String buildToken(String subject, Map<String, Object> extra, Duration ttl, String type) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(extra)
                .setSubject(subject)
                .claim("typ", type)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ttl)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) { return extractClaim(token, Claims::getSubject); }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = parseAllClaims(token);
        return resolver.apply(claims);
    }

    public boolean isAccessToken(String token) { return "access".equals(parseAllClaims(token).get("typ")); }
    public boolean isRefreshToken(String token) { return "refresh".equals(parseAllClaims(token).get("typ")); }

    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isExpired(token);
    }

    public boolean isExpired(String token) {
        return parseAllClaims(token).getExpiration().before(new Date());
    }

    private Claims parseAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
