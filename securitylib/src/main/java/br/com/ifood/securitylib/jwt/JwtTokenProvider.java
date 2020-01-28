package br.com.ifood.securitylib.jwt;

import br.com.ifood.securitylib.dto.NetworkDTO;
import br.com.ifood.securitylib.exceptions.UnauthorizedException;
import br.com.ifood.securitylib.utils.NetworkUtils;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenProvider {

    public static final String UNAUTHORIZED_ERROR = "Expired or invalid JWT token";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private JwtProperties jwtProperties;

    private String secretKey;

    @Autowired
    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER);

        return (!Objects.isNull(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) ?
                bearerToken.substring(7) : null;
    }

    public boolean validateToken(String token) {

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return (!claims.getBody().getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error(UNAUTHORIZED_ERROR);
            throw new UnauthorizedException(UNAUTHORIZED_ERROR);
        }
    }

    public String createToken(String username, HttpServletRequest request) {
        NetworkDTO network = NetworkUtils.getDeviceAddresses.apply(request);

        return Jwts.builder()
                .setSubject(username)
                .claim("mac", network.getMacAddress())
                .claim("ip", network.getIpAddress())
                .setIssuer(jwtProperties.getSecret())
                .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Date calculateExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + jwtProperties.getValidityInMilliseconds());
    }
}
