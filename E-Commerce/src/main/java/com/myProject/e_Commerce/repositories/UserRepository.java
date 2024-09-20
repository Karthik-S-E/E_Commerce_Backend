package com.myProject.e_Commerce.repositories;

import com.myProject.e_Commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String name);

    boolean existsByUserName(String user1);

    boolean existsByEmail(String user1);
}
