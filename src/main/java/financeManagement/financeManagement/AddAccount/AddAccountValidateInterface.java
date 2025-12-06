package financeManagement.financeManagement.AddAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import financeManagement.financeManagement.common.AccountsTable;

@Repository
public interface AddAccountValidateInterface  extends JpaRepository<AccountsTable, Integer>{
	
	

}
