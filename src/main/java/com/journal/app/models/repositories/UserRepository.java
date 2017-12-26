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
    public List<User> findByName(String name);

    public List<User> findByEmail(String email);

    public User findByUsername(String email);

    public List<User> findByCountryId(Long countryId);

    @Query("select u from User u where (lower(u.username) = :username or lower(u.email)= :username) and u.password=:password")
    public User findByUserAndPassword(@Param("username") String username, @Param("password") String password);
}
