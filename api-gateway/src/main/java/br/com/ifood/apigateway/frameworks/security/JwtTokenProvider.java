package br.com.ifood.apigateway.frameworks.security;

import br.com.ifood.apigateway.frameworks.exceptions.UnauthorisedException;
import br.com.ifood.apigateway.infrastructures.port.application.UserApplicationPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String UNAUTHORIZED_ERROR = "Expired or invalid JWT token";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private JwtProperties jwtProperties;

    private UserApplicationPort userApplication;

    private String secretKey;

    @Autowired
    public JwtTokenProvider(JwtProperties jwtProperties, UserApplicationPort userApplication) {
        this.jwtProperties = jwtProperties;
        this.userApplication = userApplication;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes());
    }



    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userApplication.findByUsername(getUsername(token), BEARER_PREFIX+token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
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
            throw new UnauthorisedException(UNAUTHORIZED_ERROR);
        }
    }
}
