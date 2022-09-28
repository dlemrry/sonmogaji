package com.ssafy.sonmogaji.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.sonmogaji.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	List<Member> findByMemberAddress(String memberAddress);

}
