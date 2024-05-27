package com.example.greatestdiarybackend.details;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.entities.roles.Role;
import com.example.greatestdiarybackend.services.user.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);

        List<GrantedAuthority> roles = getGrantedAuthorities(user);

        return CustomUserDetails.build(
                user,
                roles
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(@NotNull User user) {
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(user.isEnable() ? user.getRole() : Role.BANNED.getAuthority()));

        return role;
    }
}
