package br.com.ifood.adminservice.infrastructure.adapter.data;

import br.com.ifood.adminservice.domain.User;
import br.com.ifood.adminservice.infrastructure.port.data.UserDataPort;
import br.com.ifood.adminservice.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataAdapter implements UserDataPort {

    private UserRepository repository;

    @Autowired
    public UserDataAdapter(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
