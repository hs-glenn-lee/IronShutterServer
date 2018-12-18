package com.ironshutter.web.domain.model.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query("select account from Account account "
			+ " join fetch account.sign sign"
			+ " join fetch account.user user"
			+ " where sign.username = :username "
			+ " and sign.hashedPassword = :hashedPassword")
	public Optional<Account> findByUsernameAndHashedPassword(@Param("username") String username, @Param("hashedPassword") String hashedPassword);

}
