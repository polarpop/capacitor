package com.infosys.capacitor.repository;

import com.infosys.capacitor.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByUsername(String username);

  List<User> findByFirst_name(String first_name);

  List<User> findByLast_name(String last_name);

  List<User> findByEmail(String email);

  List<User> findByManager(User manager);

  User findBySys_id(long id);
}