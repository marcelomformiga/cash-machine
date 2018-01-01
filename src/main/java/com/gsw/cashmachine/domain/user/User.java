package com.gsw.cashmachine.domain.user;

import com.gsw.cashmachine.domain.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * A classe User representa o modelo de dados de usuarios no banco cash.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 15, nullable = false)
    private String username;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "authorities")
    private String authorities;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;

    public User(){}

    public User(Long id, String username, String password, String authorities, String email, Account account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.email = email;
        this.account = account;
    }

    public User(String username, String password, String email, String authorities, Account account) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
