package financeManagement.financeManagement.GetTransactionById;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import financeManagement.financeManagement.common.AccountsTable;

@Repository
public interface GetTransactionByIdAccountInterface extends JpaRepository<AccountsTable, Long>{

}
