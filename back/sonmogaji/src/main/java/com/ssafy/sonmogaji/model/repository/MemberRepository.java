package com.ssafy.sonmogaji.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.sonmogaji.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findByMemberAddress(String memberAddress);

}
