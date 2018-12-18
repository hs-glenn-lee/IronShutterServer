package com.ironshutter.web.domain.model.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SignRepository extends JpaRepository<Sign, Long>{
	
	@Query("select sign from Sign sign "
			+ " where sign.username = :username ")
	public Optional<Sign> findByUsername(@Param("username")String username);
}
