package net.contal.demo.modal;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "bank_transaction")
public class BankTransaction {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_account_id")
    private CustomerAccount customerAccount;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount( double transcationAmount ){
        this.transactionAmount = transcationAmount;
    }

    public Date getTransactionDate(){
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
