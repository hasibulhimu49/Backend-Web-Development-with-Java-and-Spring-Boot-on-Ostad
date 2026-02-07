package com.authentication.Auth.repository;

import com.authentication.Auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<UserDetails> findByEmail(String username);

    boolean existsByEmail( String email);

    Optional<User> findByEmailAndVerified(String email, boolean b);

    List<User> findAllByVerified(boolean b);
}
