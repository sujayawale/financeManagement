package financeManagement.financeManagement.common;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name="transactions", schema="financemanagement")
public class ExpenseTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="txn_id")
	private Long txnId;
	
	@Column(name="account_id", nullable = false)
	private Long accountId;
	
	@Column(name="typee", nullable = false, length = 20)
	private String type;
	
	@Column(name="category", nullable = false, length = 50)
	private String category;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="txn_time")
	private LocalDateTime txnTime=LocalDateTime.now();
}
