package com.bloomscorp.aster.alfred.dao.repository;

import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import org.springframework.data.jpa.repository.JpaRepository;

interface AuthenticationLogJpaRepository extends JpaRepository<AsterAuthenticationLog, Long> {
}
