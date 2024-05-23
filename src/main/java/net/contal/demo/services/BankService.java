package net.contal.demo.services;

import net.contal.demo.DbUtils;
import net.contal.demo.modal.CustomerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

/**
 * TODO complete this service class
 * TODO use BankServiceTest class
 */
@Service
@Transactional
public class BankService {

    //USE this class to access database , you can call openASession to access database
    private final DbUtils dbUtils;
    @Autowired
    public BankService(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }


    /**
     * TODO implement the rest , populate require fields for CustomAccount (Generate Back account by using AccountNumberUtil )
     * Save customAccount to database
     * return AccountNumber
     * @param customerAccount populate this (firstName , lastName ) already provided
     * @return accountNumber
     */
    public String createAnAccount(CustomerAccount customerAccount){
            // TODO implement the rest


        dbUtils.openASession().saveOrUpdate(customerAccount);
        //TODO return bank account number
        return "";
    }


    /**
     * TODO implement this functions
     * @param accountNumber target account number
     * @param amount amount to register as transaction
     * @return boolean , if added as transaction
     */
    public boolean addTransactions(int accountNumber , Double amount){

        /**
         *TODO
         * Find and account by using accountNumber (Only write  the query in hql String  )
         * create Transaction for account with provided  amount
         * return true if added , return false if account dont exist , or amount is null
         */

        /** TODO write Query to get account by number un comment section below , catch query   */
// HAlf code
//                 String hql = "";
//                 this.dbUtils.openASession().createQuery(hql,CustomerAccount.class)
//                .setParameter("accountNumber",accountNumber)
//                .getResultList();


        return false;
    }


    /**
     * TODO implement this functions
     * @param accountNumber target account
     * @return account balance
     */
    public double getBalance(int accountNumber){

        /**
         *TODO
         *  find the account by this account Number
         *  sum total of transactions belong to account
         *  return sum of amount
         *
         */

//                 String hql = "";
//                 this.dbUtils.openASession().createQuery(hql,CustomerAccount.class)
//                .setParameter("accountNumber",accountNumber)
//                .getResultList();



        return 0d;
    }


    /**
     * TODO implement this functions
     * ADVANCE TASK
     * @param accountNumber accountNumber
     * @return HashMap [key: date , value: double]
     */
    public Map<Date,Double> getDateBalance(int accountNumber){
        /**
         *TODO
         * get all bank Transactions for this account number
         * Create map , Each Entry should hold a Date as a key and value as balance on key date from start of account
         * Example data [01/01/1992 , 2000$] balance 2000$ that date 01/01/1992
         */

        return null;
    }


}
