package com.gsw.cashmachine.authentication.factory;

import com.gsw.cashmachine.authentication.user.AuthenticationUser;
import com.gsw.cashmachine.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * A classe UserFactory e responsavel por criar objetos do tipo UserDetails
 * @author Eduardo Alves
 * @version 1.0
 */
public class UserFactory {

    /**
     * O metodo recebe como parametro um usuario do dominio e retorna
     * um usuario autenticado pelo spring
     * @param userEntity
     * @return AuthenticationUser
     */
    public static AuthenticationUser create(User userEntity) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userEntity.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }
        return new AuthenticationUser(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
