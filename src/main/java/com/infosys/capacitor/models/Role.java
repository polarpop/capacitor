package com.infosys.capacitor.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Role")
@Table(name = "role")
public class Role {
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
  @Size(max = 200)
  @Column(name = "role_name", unique = true)
  private String name;


  @ManyToMany(mappedBy = "permissions")
  private final List<Permission> permissions = new ArrayList<>();

  public Role(String name) {
    this.name = name;
  }
  public Role() {
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

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Permission permission) {
    permissions.add(permission);
    permission.getRoles().add(this);
  }

  public void setPermissions(List<Permission> permissionsList) {
    Iterator<Permission> perms = permissionsList.iterator();
    while (perms.hasNext()) {
      Permission perm = perms.next();
      if (!(permissions.contains(perm))) {
        permissions.add(perm);
        perm.getRoles().add(this);
      }
    }
  }

  public void removePermissions(Permission permission) {
    permissions.remove(permission);
    permission.getRoles().remove(this);
  }

  public void removePermissions(List<Permission> permissionsList) {
    permissions.removeAll(permissionsList);
    Iterator<Permission> perms = permissionsList.iterator();

    while (perms.hasNext()) {
      Permission perm = perms.next();
      if ((perm.getRoles().contains(this))) {
        perm.getRoles().remove(this);
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Role))
      return false;
    return id != null && id.equals(((Role) o).getId());
  }
}