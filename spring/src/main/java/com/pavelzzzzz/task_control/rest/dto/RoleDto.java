package com.pavelzzzzz.task_control.rest.dto;

public class RoleDto {
  private Integer id;
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static RoleDtoBuilder builder(){
    return new RoleDtoBuilder();
  }


  public static final class RoleDtoBuilder {
    private RoleDto roleDto;

    private RoleDtoBuilder() {
      roleDto = new RoleDto();
    }

    public RoleDtoBuilder id(Integer id) {
      roleDto.setId(id);
      return this;
    }

    public RoleDtoBuilder name(String name) {
      roleDto.setName(name);
      return this;
    }

    public RoleDto build() {
      return roleDto;
    }
  }
}
