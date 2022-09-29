package com.ssafy.sonmogaji.security;

import com.ssafy.sonmogaji.model.entity.Member;
import com.ssafy.sonmogaji.model.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private String memberAddress;
    private Integer nonce;
    private String userAuth;

    public static UserPrincipal create(Member member) {

        return new UserPrincipal(
          member.getMemberAddress(),
          member.getNonce(),
          member.getRole().getKey()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.userAuth));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(memberAddress, that.memberAddress);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(memberAddress);
    }


}
