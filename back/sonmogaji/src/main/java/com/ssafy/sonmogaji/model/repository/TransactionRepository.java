package com.ssafy.sonmogaji.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.sonmogaji.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
