package com.ssafy.sonmogaji.model.reopository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.sonmogaji.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
