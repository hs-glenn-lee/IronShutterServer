package com.ironshutter.web.infrastructure.persistence.springDataJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ironshutter.web.domain.model.account.Account;

public interface AccountRepositorySpringJpa extends JpaRepository<Account, Long>{
	
	@Query("select account from Account account "
			+ " join fetch account.user "
			+ " where account.id = :id")
	public Account findWithUser(@Param("id") Long id);
	
/*	
	@Query("select act from Account act "
			+ " join fetch act.accountSetting "
			+ " where act.id = :id")
	public List<Account> findWithAccountSetting(@Param("id")Long id);
	
	public Account findByUsername(String username);
	
	@Query("select account from Account account "
			+ " where account.username = :username "
			+ " and account.password = :password")
	public List<Account> findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
	
	
	@Query(value="select * from accounts a where id = :id", nativeQuery=true)
	public Account test(@Param("id") Long id);
	*/
	
	
}
