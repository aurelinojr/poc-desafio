package com.free.fluxocaixa.infra.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (username.equals("user")) {
            return User.withUsername(username).password(encoder.encode("user")).roles("user").build();
        } else if(username.equals("admin")) {
            return User.withUsername(username).password(encoder.encode("admin")).roles("user","admin").build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado!");
    }
}
