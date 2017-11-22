package com.journal.app.models.repositories;

import com.journal.app.models.domain.AccountGroup;
import com.journal.app.models.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountGroupRepository extends JpaRepository<AccountGroup, Long> {

}
