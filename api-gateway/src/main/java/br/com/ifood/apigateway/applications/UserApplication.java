package br.com.ifood.apigateway.applications;

import br.com.ifood.apigateway.frameworks.exceptions.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import br.com.ifood.apigateway.frameworks.exceptions.ServiceUnavailableException;
import br.com.ifood.apigateway.infrastructures.port.application.UserApplicationPort;
import br.com.ifood.apigateway.infrastructures.port.data.UserDataPort;
import br.com.ifood.apigateway.infrastructures.rest.services.adminService.dto.UserClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserApplication implements UserApplicationPort {

    private UserDataPort userData;

    @Autowired
    public UserApplication(UserDataPort userData){
        this.userData = userData;
    }

    @Override
    public UserDetails findByUsername(String username) throws UsernameNotFoundException {

        UserClientDTO user = userData.findByUsername(username);

        validateUnavailable(user);

        if(Objects.isNull(user)){
            throw new NotFoundException("User not found");
        }

        List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));

        return new User(username,
                user.getPassword(), true, true, true,
                true, grantedAuthorities);

    }

    private void validateUnavailable(UserClientDTO user){
        if( user.isUnavailable() ){
            throw new ServiceUnavailableException("It is not possible to validate the user");
        }
    }
}
