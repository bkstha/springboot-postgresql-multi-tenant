package com.journal.app.models.repositories;

import com.journal.app.models.domain.UniqueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniqueIdRepository extends JpaRepository<UniqueId, Long> {

}
