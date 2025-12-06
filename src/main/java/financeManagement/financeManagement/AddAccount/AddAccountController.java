package financeManagement.financeManagement.AddAccount;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import financeManagement.financeManagement.common.AccountsTable;
import financeManagement.financeManagement.common.Constants;

@RestController
public class AddAccountController {
	
	private AddAccountValidateInterface addAccountValidateInterface;
	
	public AddAccountController(AddAccountValidateInterface addAccountValidateInterface) {
		this.addAccountValidateInterface=addAccountValidateInterface;
	}
	
	@PostMapping("/api/v1/accounts")
	private ResponseEntity<?> addAccount(@RequestBody  AccountsTable accountsTable){
		
		ResponseEntity<?>check= validate(accountsTable);
		if(null!=check) {
			return check;
		}
		
		addAccountValidateInterface.save(accountsTable);

		Map<String,Object>response=new HashMap<String, Object>();
		response.put(Constants.STATUS, Constants.SUCCESS);
		response.put(Constants.MESSAGE, Constants.ACCOUNT_CREATED);
		Map<String,Object>data=new HashMap<String, Object>();
		data.put(Constants.ACCOUNTID, accountsTable.getAccountId());
		data.put(Constants.BALANCE, accountsTable.getBalance());
		response.put(Constants.DATA, data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private ResponseEntity<?> validate(AccountsTable accountsTable) {
		Map<String,Object>response=new HashMap<String, Object>();
		if(null==accountsTable.getAccountName()) {
			response.put(Constants.STATUS, Constants.ACCOUNTNAMEISBLANK);
			return ResponseEntity.badRequest().body(response);
		}
		if(null==accountsTable.getUserName()) {
			response.put(Constants.STATUS, Constants.USERNAMEISBLANK);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(null==accountsTable.getMobileNumber()) {
			response.put(Constants.STATUS, Constants.MOBILENUMBERISBLANK);
			return ResponseEntity.badRequest().body(response);
		}
		return null;
	}
}
