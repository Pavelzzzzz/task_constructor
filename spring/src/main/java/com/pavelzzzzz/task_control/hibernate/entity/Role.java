package com.pavelzzzzz.task_control.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Role {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  @NotBlank
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer roleId) {
    this.id = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String roleName) {
    this.name = roleName;
  }
}
