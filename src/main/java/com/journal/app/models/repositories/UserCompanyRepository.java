package com.journal.app.models.repositories;

import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

    @Query("select u from UserCompany u where u.user.id = :userId and u.company.id=:companyId")
    UserCompany findByUserIdAndCompanyId(@Param("userId") Long userId, @Param("companyId") Long companyId);
}
