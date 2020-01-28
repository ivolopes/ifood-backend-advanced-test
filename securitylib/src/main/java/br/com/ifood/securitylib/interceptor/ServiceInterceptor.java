package br.com.ifood.securitylib.interceptor;

import br.com.ifood.securitylib.exceptions.ForbiddenException;
import br.com.ifood.securitylib.exceptions.UnauthorizedException;

import br.com.ifood.securitylib.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static br.com.ifood.securitylib.jwt.JwtTokenProvider.UNAUTHORIZED_ERROR;

@Component
public class ServiceInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String authorization = jwtTokenProvider.resolveToken(request);

        if(Objects.isNull(authorization)){
            throw new ForbiddenException(null);
        }

        if( !jwtTokenProvider.validateToken(authorization) ){
            throw new UnauthorizedException(UNAUTHORIZED_ERROR);
        }

        return true;
    }

}
