package com.refactorizando.observability.user.repository;

import com.refactorizando.observability.user.domain.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    List<UserDetail> findAllByName(String name, Pageable pageable);


}
