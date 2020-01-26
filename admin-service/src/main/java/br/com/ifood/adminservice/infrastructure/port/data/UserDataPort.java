package br.com.ifood.adminservice.infrastructure.port.data;

import br.com.ifood.adminservice.domain.User;

public interface UserDataPort {

    User save(User user);

    User findByUsername(String username);

}
