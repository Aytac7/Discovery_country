package com.example.discovery_country.dao.repository.auth;

import com.example.discovery_country.dao.entity.auth.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
    Optional<User> findUserByEmail(String email);

    User findUserById(long id);
//    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.phonePrefix = :phonePrefix AND u.phoneNumber = :phoneNumber")
//    boolean existsByPhonePrefixAndPhoneNumber(String phonePrefix, String phoneNumber);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);


    @Modifying
    @Transactional
    @Query("update User u set u.failedAttempt = ?1 WHERE u.email = ?2")
    void updateFailedAttempts(int failAttempts, String email);

    @Modifying
    @Query("UPDATE User u SET u.accountNonLocked = ?1, u.lockTime = ?2 WHERE u.email = ?3")
    void updateLockStatus(boolean accountNonLocked, Date lockTime, String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}