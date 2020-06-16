package com.infosys.capacitor.model.request;

import com.infosys.capacitor.dao.User;

public class CreateUserRequest {
  private String firstName;
  private String lastName;
  private String manager;

  public CreateUserRequest() {
    super();
  }

  public CreateUserRequest(String firstName, String lastName, String email, User manager) {

  }

}