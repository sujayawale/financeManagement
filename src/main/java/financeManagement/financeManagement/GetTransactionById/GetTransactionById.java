package financeManagement.financeManagement.GetTransactionById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import financeManagement.financeManagement.common.AccountsTable;
import financeManagement.financeManagement.common.Constants;

@RestController
public class GetTransactionById {
	
	private GetTransactionByIdAccountInterface getTransactionByIdAccountInterface;
	private GetTransactionByIdtransactionsInterface getTransactionByIdtransactionsInterface;
	
	public GetTransactionById(GetTransactionByIdAccountInterface getTransactionByIdAccountInterface, GetTransactionByIdtransactionsInterface getTransactionByIdtransactionsInterface){
		this.getTransactionByIdAccountInterface=getTransactionByIdAccountInterface;
		this.getTransactionByIdtransactionsInterface=getTransactionByIdtransactionsInterface;
	}

	@GetMapping("/api/v1/accounts/{accountId}")
	private ResponseEntity<?> getAllTransactionByAccountId(@PathVariable Long accountId){
		Map<String,Object>response=new HashMap<String, Object>();
		
		Optional<AccountsTable> accountsTable=getTransactionByIdAccountInterface.findById(accountId);
		
		
		if(!accountsTable.isPresent()) {
			response.put(Constants.MESSAGE, Constants.NOTABLETOFINDACCOUNTID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		
		AccountsTable accountsTableData=accountsTable.get();
		response.put(Constants.ACCOUNTID, accountsTableData.getAccountId());
		response.put(Constants.ACCOUNTNAME, accountsTableData.getAccountName());
		response.put(Constants.CURRENTBALANCE, accountsTableData.getBalance());
		ArrayList<Map<String, Object>>transactionsData=getTransactionByIdtransactionsInterface.getTransactionData(accountId);
		if(transactionsData.isEmpty()) {
			Map<String,Object>transactionMap=new HashMap<String, Object>();
			transactionMap.put(Constants.MESSAGE, Constants.NOTRANSACTIONDATAFOUND);
			transactionsData.add(transactionMap);
		}
		response.put(Constants.TRANSACTIONS, transactionsData);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
