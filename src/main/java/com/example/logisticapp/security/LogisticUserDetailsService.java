package com.example.logisticapp.security;

import com.example.logisticapp.model.LogisticUser;
import com.example.logisticapp.repository.LogisticUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LogisticUserDetailsService implements UserDetailsService {

    @Autowired
    LogisticUserRepository logisticUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LogisticUser logisticUser = logisticUserRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(username, logisticUser.getPassword(), logisticUser.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
    }
}
