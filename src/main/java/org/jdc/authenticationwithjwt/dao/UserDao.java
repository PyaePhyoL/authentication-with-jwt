package org.jdc.authenticationwithjwt.dao;

import org.jdc.authenticationwithjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("""
    select u from User u where u.email = :email
""")
    Optional<User> findByEmail(String email);
}
