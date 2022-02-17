package homework;

import java.util.Scanner;

public class TestAccount1 {

    //************************************************************
    // TestAccounts1
    // A simple program to test the numAccts method of the
    // Account class.
    //************************************************************
    public static void main(String[] args) {
        Account testAcct;
        Scanner scan = new Scanner(System.in);
        System.out.println("How many accounts would you like to create?"); int num =
            scan.nextInt();
        for (int i=1; i<=num; i++)
        {

            testAcct = new Account(100, "Name" + i);
            System.out.println("\nCreated account " + testAcct);
            // Your code here: call method to print out how many accounts have been created so far
        }
        testAcct = new Account(100, "toClose" + 19);
        System.out.println("\n\nTotal no of account "+Account.getNumAccounts());
        System.out.println("\n\nDELETED "+testAcct.close());
        System.out.println("\n\nTotal no of account "+Account.getNumAccounts());
    }
}