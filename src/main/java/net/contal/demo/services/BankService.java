package net.contal.demo.services;

import net.contal.demo.DbUtils;
import net.contal.demo.modal.CustomerAccount;
import net.contal.demo.modal.BankTransaction;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Transactional
public class BankService {

    //USE this class to access database , you can call openASession to access database
    private final DbUtils dbUtils;
    @Autowired
    public BankService(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    public String createAnAccount(CustomerAccount customerAccount){
        Session session = dbUtils.openASession();
        try{
            session.saveOrUpdate(customerAccount);
            session.getTransaction().commit();
            return String.valueOf(customerAccount.getAccountNumber());
        } catch ( Exception e ){
            session.getTransaction().rollback();
            throw e;
        }finally {
            if (session != null){
                session.close();
            }
        }
    }



    public boolean addTransactions(int accountNumber , Double amount){
        if (amount == null){
            return false;
        }

        Session session = dbUtils.openASession();
        try{
            String hql = "FROM CustomerAccount WHERE accountNumber = :accountNumber";
            CustomerAccount account = session.createQuery(hql, CustomerAccount.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();
            if (account != null){
                BankTransaction transaction = new BankTransaction();
                transaction.setCustomerAccount(account);
                transaction.setTransactionAmount(amount);
                transaction.setTransactionDate(new Date());
                session.save(transaction);
                session.getTransaction().commit();
                return true;
            }else {
                session.getTransaction().rollback();;
                return false;
            }
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;

        }finally {
            if (session != null){
                session.close();
            }
        }
    }


    public double getBalance(int accountNumber){
        Session session = dbUtils.openASession();
        try {

            String hql = "FROM CustomerAccount WHERE accountNumber = :accountNumber";
            CustomerAccount account = session.createQuery(hql, CustomerAccount.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();
            if (account != null) {
                // 연관된 모든 거래의 금액 합산
                hql = "SELECT SUM(t.transactionAmount) FROM BankTransaction t WHERE t.customerAccount.id = :accountId";
                Double balance = (Double) session.createQuery(hql)
                        .setParameter("accountId", account.getId())
                        .uniqueResult();
                session.getTransaction().commit();
                return balance != null ? balance : 0.0;
            } else {
                session.getTransaction().rollback();
                return 0.0;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }



    public Map<Date,Double> getDateBalance(int accountNumber){
        Session session = dbUtils.openASession();
        try {

            String hql = "FROM BankTransaction WHERE customerAccount.accountNumber = :accountNumber ORDER BY transactionDate";
            List<BankTransaction> transactions = session.createQuery(hql, BankTransaction.class)
                    .setParameter("accountNumber", accountNumber)
                    .getResultList();

            Map<Date, Double> balanceMap = new TreeMap<>();
            double cumulativeBalance = 0;

            for (BankTransaction transaction : transactions) {
                cumulativeBalance += transaction.getTransactionAmount();
                balanceMap.put(transaction.getTransactionDate(), cumulativeBalance);
            }

            session.getTransaction().commit();
            return balanceMap;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
