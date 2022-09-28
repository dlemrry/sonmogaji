package com.ssafy.sonmogaji.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.sonmogaji.model.entity.Member;
import com.ssafy.sonmogaji.model.entity.Signee;

public interface SigneeRepository extends JpaRepository<Signee, Long>{
	List<Signee> findByMember(Member member);
}
