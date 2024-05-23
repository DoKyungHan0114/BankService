package net.contal.demo.modal;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CustomerAccount {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private List<BankTransaction> transactions;

    //TODO implement extra properties and create  setter and getter for each

    //Set getter and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }
}
