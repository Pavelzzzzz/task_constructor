package com.pavelzzzzz.task_control.security.service;

import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Set<RoleDto> roleDtoSet;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleDtoSet.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role.getName())
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoleDtoSet() {
        return roleDtoSet;
    }

    public void setRoleDtoSet(Set<RoleDto> roleDtoSet) {
        this.roleDtoSet = roleDtoSet;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public static CustomUserDetailsBuilder builder() {
        return new CustomUserDetailsBuilder();
    }

    public static final class CustomUserDetailsBuilder {
        private CustomUserDetails customUserDetails;

        private CustomUserDetailsBuilder() {
            customUserDetails = new CustomUserDetails();
        }

        public CustomUserDetailsBuilder username(String username) {
            customUserDetails.setUsername(username);
            return this;
        }

        public CustomUserDetailsBuilder password(String password) {
            customUserDetails.setPassword(password);
            return this;
        }

        public CustomUserDetailsBuilder roleDtoSet(Set<RoleDto> roleDtoSet) {
            customUserDetails.setRoleDtoSet(roleDtoSet);
            return this;
        }

        public CustomUserDetailsBuilder isEnabled(boolean isEnabled) {
            customUserDetails.setEnabled(isEnabled);
            return this;
        }

        public CustomUserDetails build() {
            return customUserDetails;
        }
    }
}
