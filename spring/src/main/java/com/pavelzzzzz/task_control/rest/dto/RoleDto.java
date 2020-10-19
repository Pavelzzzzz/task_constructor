package com.pavelzzzzz.task_control.rest.dto;

public class RoleDto {
  private Integer roleId;
  private String roleName;

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public static RoleDtoBuilder builder(){
    return new RoleDtoBuilder();
  }


  public static final class RoleDtoBuilder {
    private RoleDto roleDto;

    private RoleDtoBuilder() {
      roleDto = new RoleDto();
    }

    public RoleDtoBuilder roleId(Integer roleId) {
      roleDto.setRoleId(roleId);
      return this;
    }

    public RoleDtoBuilder roleName(String roleName) {
      roleDto.setRoleName(roleName);
      return this;
    }

    public RoleDto build() {
      return roleDto;
    }
  }
}
