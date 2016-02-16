package com.github.dnvriend.repository.reader

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Reader implements UserDetails {

    @Id
    String username
    String fullname
    String password

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        [new SimpleGrantedAuthority("READER")]
    }

    @Override
    boolean isAccountNonExpired() {
        true
    }

    @Override
    boolean isAccountNonLocked() {
        true
    }

    @Override
    boolean isCredentialsNonExpired() {
        true
    }

    @Override
    boolean isEnabled() {
        true
    }
}
