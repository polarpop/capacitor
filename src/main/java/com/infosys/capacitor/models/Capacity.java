package com.infosys.capacitor.models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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


  public Capacity() {
    super();
  }
}