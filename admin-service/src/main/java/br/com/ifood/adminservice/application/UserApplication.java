package br.com.ifood.adminservice.application;

import br.com.ifood.adminservice.domain.User;
import br.com.ifood.adminservice.framework.exceptions.BadRequestException;
import br.com.ifood.adminservice.infrastructure.port.application.UserApplicationPort;
import br.com.ifood.adminservice.infrastructure.port.data.UserDataPort;
import br.com.ifood.adminservice.infrastructure.rest.controller.v1.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserApplication implements UserApplicationPort {

    private UserDataPort userData;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserApplication(UserDataPort userData, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userData = userData;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto save(String fullname, String username, String password) {

        if( password.length() < 6 ){
            throw new BadRequestException("A senha tem que ter no minimo 6 caracteres");
        }

        User user = userData.findByUsername(username);

        if( user != null){
            throw new BadRequestException("E-mail já cadastrado");
        }

        user = User.of(fullname, username, bCryptPasswordEncoder.encode(password));

        user = userData.save(user);

        return this.convertToDto(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userData.findByUsername(username);

        if( user != null ){
            return this.convertToDto(user);
        }else{
            return  null;
        }
    }

    private UserDto convertToDto(User user){
        return UserDto.builder().id(user.getId())
                .fulllname(user.getFullname())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
