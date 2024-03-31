package com.apiBoaventuraMarketplace.service;

import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClienteEntity cliente = clienteRepository.findByLogin(username).get();
        return UserDetailsImpl.build(cliente);
    }
}
