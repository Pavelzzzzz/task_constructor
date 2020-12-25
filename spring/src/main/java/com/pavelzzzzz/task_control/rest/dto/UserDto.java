package com.pavelzzzzz.task_control.rest.dto;

import java.util.Set;

public class UserDto {

    private Integer id;
    private String username;
    private boolean isEnabled;
    private Set<RoleDto> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public static final class UserDtoBuilder {
        private UserDto userDto;

        private UserDtoBuilder() {
            userDto = new UserDto();
        }

        public UserDtoBuilder id(Integer id) {
            userDto.setId(id);
            return this;
        }

        public UserDtoBuilder username(String username) {
            userDto.setUsername(username);
            return this;
        }

        public UserDtoBuilder isEnabled(Boolean isEnabled) {
            userDto.setEnabled(isEnabled);
            return this;
        }

        public UserDtoBuilder roles(Set<RoleDto> roles) {
            userDto.setRoles(roles);
            return this;
        }

        public UserDto build() {
            return userDto;
        }
    }
}
