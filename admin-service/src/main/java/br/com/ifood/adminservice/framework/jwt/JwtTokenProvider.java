package br.com.ifood.adminservice.framework.jwt;

import br.com.ifood.adminservice.framework.utils.NetworkUtils;
import br.com.ifood.adminservice.infrastructure.rest.controller.v1.login.dto.NetworkDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

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
