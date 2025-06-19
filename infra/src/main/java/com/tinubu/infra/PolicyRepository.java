package com.tinubu.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {
}
