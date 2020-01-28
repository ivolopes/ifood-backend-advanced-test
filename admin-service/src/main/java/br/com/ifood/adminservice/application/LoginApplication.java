package br.com.ifood.adminservice.application;

import br.com.ifood.adminservice.domain.User;
import br.com.ifood.securitylib.exceptions.UnauthorizedException;
import br.com.ifood.securitylib.jwt.JwtTokenProvider;
import br.com.ifood.adminservice.infrastructure.port.application.LoginApplicationPort;
import br.com.ifood.adminservice.infrastructure.port.data.UserDataPort;
import br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class LoginApplication implements LoginApplicationPort {

    private static final String LOGIN_ERROR = "Username or password is not correct";

    private JwtTokenProvider jwtTokenProvider;
    private UserDataPort userData;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginApplication(JwtTokenProvider jwtTokenProvider, UserDataPort userData, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userData = userData;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String login(UserDto userDto, HttpServletRequest request) {

        User user = userData.findByUsername(userDto.getUsername());

        validateUser(user, userDto);

        return jwtTokenProvider.createToken(user.getUsername(), request);
    }

    private void validateUser(User user, UserDto userDto){
        if(Objects.isNull(user)){
            throw new UnauthorizedException(LOGIN_ERROR);
        }

        boolean validated = bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword());

        if( !validated ){
            throw new UnauthorizedException(LOGIN_ERROR);
        }
    }
}
