package com.gsw.cashmachine.domain.transaction;

import com.gsw.cashmachine.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eduardo on 30/12/17.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select tr from Transaction tr where tr.account.user.username =?1")
    List<Transaction> findTransactionsByUsername(final String username);
}
