package net.contal.demo.modal;

import javax.persistence.*;

//TODO complete this class
@Entity
@Table
public class BankTransaction {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private CustomerAccount customerAccount;
    //TODO implement extra properties and create  setter and getter for each


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
}
