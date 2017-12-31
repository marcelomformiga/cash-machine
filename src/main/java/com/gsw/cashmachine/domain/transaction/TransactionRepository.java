package com.gsw.cashmachine.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eduardo on 30/12/17.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
