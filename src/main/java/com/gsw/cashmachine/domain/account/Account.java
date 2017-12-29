package com.gsw.cashmachine.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsw.cashmachine.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A classe Account representa o modelo de dados de conta no banco.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Data
@Entity
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agency", length = 4, nullable = false)
    private String agency;

    @Column(name = "account", length = 6, nullable = false)
    private String account;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @JsonIgnore
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "account", targetEntity = Transaction.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

    public Account() {}

    public Account(String agency, String account, Double balance, User user) {
        this.agency = agency;
        this.account = account;
        this.balance = balance;
        this.user = user;
    }

    public Account(String agency, String account, Double balance, User user, List<Transaction> transactionList) {
        this.agency = agency;
        this.account = account;
        this.balance = balance;
        this.user = user;
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "agency='" + agency + '\'' +
                ", account='" + account + '\'' +
                ", balance=" + balance +
                '}';
    }
}
