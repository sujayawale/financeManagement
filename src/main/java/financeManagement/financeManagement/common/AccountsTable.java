package financeManagement.financeManagement.common;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name="accounts", schema = "financemanagement")
public class AccountsTable {
	
	@Id
	@Column(name="account_id")
	private Long accountId;
	
	@Column(name="account_name", nullable = false, length = 100)
	private String accountName;
	
	@Column(name="balance")
	private Double balance=0.00;
	
	@Column(name="created_at", updatable = false)
	private LocalDateTime createdAt= LocalDateTime.now();
	
	@Column(name="user_name", nullable = false, length = 100)
	private String userName;
	
	@Column(name="Mobile_number", nullable = false, length = 15)
	private String mobileNumber;
	
	 @PrePersist
	    public void generateAccountId() {
	        if (this.accountId == null) {
	            String time = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
	            int randomNum = (int) (Math.random() * 900) + 100;

	            String idStr = time + randomNum;
	            this.accountId = (long) Integer.parseInt(idStr); 
	        }
	    }

}
