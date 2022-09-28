package com.ssafy.sonmogaji.security;

import com.ssafy.sonmogaji.model.entity.Member;
import com.ssafy.sonmogaji.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String memberAddress) throws UsernameNotFoundException {

        Member member = memberRepository.findByMemberAddress(memberAddress)
                .orElseThrow(() -> new UsernameNotFoundException(memberAddress));

        return UserPrincipal.create(member);
    }
}
