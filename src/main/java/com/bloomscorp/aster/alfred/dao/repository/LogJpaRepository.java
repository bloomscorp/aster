package com.bloomscorp.aster.alfred.dao.repository;

import com.bloomscorp.aster.alfred.orm.AsterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogJpaRepository extends JpaRepository<AsterLog, Long> {
}
