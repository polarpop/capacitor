package com.infosys.capacitor.dao;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "Capacity")
@Table(name = "capacity")
public class Capacity {

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
  @Column(name = "pto_hours")
  private Integer ptoHours;

  @NotNull
  @Column(name = "meeting_hours")
  private Integer meetingHours;

  @NotNull
  @Column(name = "non_project_hours")
  private Integer nonProjectHours;


  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User user;


  public Capacity() {
    super();
  }

  public Long getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Float getCapacityPercentage() {
    Integer total = meetingHours + nonProjectHours + ptoHours;
    Integer capacityCalculation = total / 40;
    return capacityCalculation.floatValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Capacity))
      return false;
    return id != null && id.equals(((Capacity) o).getId());
  }
}