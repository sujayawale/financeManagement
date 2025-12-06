package financeManagement.financeManagement.GetTransactionById;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import financeManagement.financeManagement.common.ExpenseTable;

@Repository
public interface GetTransactionByIdtransactionsInterface extends JpaRepository<ExpenseTable, Long>{

	
	@Query(value = "select f.txn_id as id, f.typee as type, f.category, f.amount, f.txn_time as date from financemanagement.transactions f where f.account_id=:accountId", nativeQuery = true)
	ArrayList<Map<String, Object>> getTransactionData(@Param("accountId")Long accountId);

}
