package com.gsw.cashmachine.domain.account;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A classe Transaction representa o modelo de dados de uma transacao no banco.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Data
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transaction_type;

    public Transaction() {
    }

    public Transaction(Account account, BigDecimal value, TransactionType transaction_type) {
        this.account = account;
        this.value = value;
        this.transaction_type = transaction_type;
    }
}
