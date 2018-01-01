package com.gsw.cashmachine.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select tr from Transaction tr where tr.account.user.username =?1")
    List<Transaction> findTransactionsByUsername(final String username);
}
