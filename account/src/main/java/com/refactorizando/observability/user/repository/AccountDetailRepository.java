package com.refactorizando.observability.user.repository;

import com.refactorizando.observability.user.domain.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountDetailRepository extends JpaRepository<AccountDetail, Long> {
}
