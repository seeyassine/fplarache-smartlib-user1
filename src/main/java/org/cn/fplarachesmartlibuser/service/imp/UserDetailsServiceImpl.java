package org.cn.fplarachesmartlibuser.service.imp;

import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO u = accountService.findByUserName(username);
        if(u == null) throw new UsernameNotFoundException("Invalid user " + username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        u.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRole()));
        });
        return new User(u.getUsername(), u.getPassword(), authorities);
    }
}
