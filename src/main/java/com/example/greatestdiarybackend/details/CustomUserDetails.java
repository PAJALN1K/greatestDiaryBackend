package com.example.greatestdiarybackend.details;

import com.example.greatestdiarybackend.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> roles;
    private boolean enable;
    private boolean nonLocked;

    public CustomUserDetails(
            String name,
            String password,
            List<GrantedAuthority> roles,
            boolean enable,
            boolean nonLocked
    ) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
        this.nonLocked = nonLocked;
    }

    public static CustomUserDetails build(
            User user,
            List<GrantedAuthority> roles
    ) {
        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                roles,
                user.isEnable(),
                user.isNonLocked()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
