package com.gsw.cashmachine.domain.account;

import com.gsw.cashmachine.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A classe UserRepository e reponsavel por pelas operacoes de CRUD de user.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public User findByAccount(String account);

    public User findById(Long id);
}
