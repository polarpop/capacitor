package com.infosys.capacitor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "User")
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long sys_id;

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
  @Column(name = "sso_id")
  private String sso_id;

  @NotNull
  @Column(name = "user_name")
  private String username;

  @NotNull
  @Column(name = "first_name")
  private String first_name;

  @NotNull
  @Column(name = "last_name")
  private String last_name;

  @Column(name = "email")
  private String email;

  @Column(name = "avatar")
  private String avatar;

  @ManyToOne
  @JoinColumn(name = "manager_id")
  private User manager;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
  private Set<User> employees = new HashSet<>();

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "user_project", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
  private Set<Project> projects = new HashSet<>();

  public User() {
    super();
  }

  public Long getSys_id() {
    return sys_id;
  }

  public void setSys_id(Long sys_id) {
    this.sys_id = sys_id;
  }

  public String getSso_id() {
    return sso_id;
  }

  public void setSso_id(String sso_id) {
    this.sso_id = sso_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAvatar() {
    if (avatar.isEmpty()) {
      String initials = String.format("%s%s", first_name.charAt(0), last_name.charAt(0));
      return initials.toUpperCase();
    }
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Set<User> getEmployees() {
    return employees;
  }

  public void setEmployee(User employee) {
    employees.add(employee);
    employee.setManager(this);
  }

  public void removeEmployee(User employee) {
    employees.remove(employee);
    employee.setManager(null);
  }

  public User getManager() {
    return manager;
  }

  public void setManager(User manager) {
    this.manager = manager;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public void setProject(Project project) {
    if (!(projects.contains(project))) {
      projects.add(project);
      project.setProjectMember(this);
    }
  }

  public void removeProject(Project project)  {
    if (projects.contains(project)) {
      projects.remove(project);
      project.removeProjectMember(this);
    }
  }

  public String getDisplayName() {
    return first_name + " " + last_name;
  }
}