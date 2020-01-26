package br.com.ifood.adminservice.infrastructure.repository;

import br.com.ifood.adminservice.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
