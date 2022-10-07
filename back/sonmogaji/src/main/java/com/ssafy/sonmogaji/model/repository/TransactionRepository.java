package com.ssafy.sonmogaji.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.sonmogaji.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

	List<Transaction> findByTxIsSecret(Boolean isSecret, Pageable pageable);
	
	Optional<Transaction> findByTxAddress(String txAddress);
	
}
