package com.infosys.capacitor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity(name = "Permission")
@Table(name = "permission")
public class Permission {

  static public enum PermissionType {
    READ,
    CREATE,
    UPDATE,
    DELETE
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  @Column(name = "created_at")
  @CreatedDate
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  @Column(name = "updated_at")
  @LastModifiedDate
  private Date updatedAt;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  @Column(name = "type")
  private PermissionType type = PermissionType.READ;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "permission_role", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private final List<Role> roles = new ArrayList<>();

  public Permission(PermissionType type) {
    this.type = type;
  }

  public Permission() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedAt() { return createdAt; }

  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

  public Date getUpdatedAt() { return updatedAt; }

  public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

  public void setType(PermissionType type) {
    this.type = type;
  }

  public PermissionType getType() {
    return type;
  }

  public boolean canRead() {
    return type.equals(PermissionType.READ);
  }

  public boolean canCreate() {
    return type.equals(PermissionType.CREATE);
  }

  public boolean canDelete() {
    return type.equals(PermissionType.DELETE);
  }

  public boolean canUpdate() {
    return type.equals(PermissionType.UPDATE);
  }

  public List<Role> getRoles() {
    return roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Permission))
      return false;
    return id != null && id.equals(((Permission) o).getId());
  }
}