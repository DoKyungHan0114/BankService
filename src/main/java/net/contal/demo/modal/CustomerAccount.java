package net.contal.demo.modal;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "Customer_account")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "account_number" , unique = true )
    private int accountNumber;

    @Column( name = "account_balance" )
    private double accountBalance;

    @OneToMany( mappedBy = "customerAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BankTransaction> transactions;

    //Set getter and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }
}
