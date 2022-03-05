package com.fundamentosplatzi.sprintboot.fundamentos.repository;

import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(" SELECT u FROM User u WHERE u.email = ?1 ")
    Optional<User> findByUserEmail(String email);

    @Query(" SELECT u FROM User u WHERE u.name  LIKE  %?1% ")
    List<User> findAndSourt(String name, Sort sort);
}
