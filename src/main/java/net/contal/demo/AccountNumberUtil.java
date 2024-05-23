package net.contal.demo;
import java.util.Random;


public abstract class AccountNumberUtil {
    public static int generateAccountNumber(){
        Random random = new Random(); // Generate a random integer with 8 digits for account number

        return 10000000 + random.nextInt(90000000);
    }

}
