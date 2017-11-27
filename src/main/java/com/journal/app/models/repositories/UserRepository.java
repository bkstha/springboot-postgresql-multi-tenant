package com.journal.app.models.repositories;

import com.journal.app.models.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByName(String name);
    public List<User> findByEmail(String email);
    public List<User> findByUsername(String email);
    public List<User> findByCountryId(Long countryId);
}
