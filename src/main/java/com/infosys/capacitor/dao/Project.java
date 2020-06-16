package com.infosys.capacitor.dao;


import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "Project")
@Table(name = "project")
public class Project {

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
  @Column(name = "project_name", unique = true)
  private String projectName;

  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  @Column(name = "project_end_date")
  private Date projectEndDate;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "user_project", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> members = new HashSet<>();

  public Project(String projectName, Date projectEndDate) {
    super();
    this.projectName = projectName;
    this.projectEndDate = projectEndDate;
  }

  public Project() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Date getProjectEndDate() {
    return projectEndDate;
  }

  public void setProjectEndDate(Date projectEndDate) {
    this.projectEndDate = projectEndDate;
  }

  public Set<User> getMembers() {
    return members;
  }

  public void setProjectMember(User member) {
    if (!(members.contains(member))) {
      members.add(member);
    }
  }

  public void removeProjectMember(User member) {
    if (members.contains(member)) {
      members.remove(member);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Project))
      return false;
    return id != null && id.equals(((Project) o).getId());
  }
}