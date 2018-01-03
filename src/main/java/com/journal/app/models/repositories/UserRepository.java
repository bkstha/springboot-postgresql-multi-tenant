package com.journal.app.models.repositories;

import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

    List<User> findByEmail(String email);

    User findByUsername(String email);

    List<User> findByCountryId(Long countryId);

    @Query("select u from User u where (lower(u.username) = :username or lower(u.email)= :username) and u.password=:password")
    User findByUserAndPassword(@Param("username") String username, @Param("password") String password);
}
