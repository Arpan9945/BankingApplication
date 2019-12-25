package com.bank.test;

import com.bank.test.Model.ApplicationMessage;
import com.bank.test.Model.User;
import com.bank.test.Utility.BankingApplicationUtility;
import com.bank.test.Utility.UserUtility;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Long askAccountNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your account number");
        Long accountNumber = scanner.nextLong();

        return accountNumber;
    }
    public static Double askAmount(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter your amount");
        Double amount = scanner.nextDouble();

        return amount;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        User user = null;
        Random random = new Random();
        BankingApplicationUtility bankingApplicationUtility = new BankingApplicationUtility();

        System.out.println("Welcome to ERupaiya Banking Application");

        while (true) {

            System.out.println("1. Create Account");
            System.out.println("2. Deposit Fund");
            System.out.println("3. Withdraw Fund");
            System.out.println("4. View Account Details");
            System.out.println("5. Delete existing account");

            System.out.println("Enter your choice");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Your Name: ");
                    String name = scanner.next();

                    System.out.print("Enter Your Address: ");
                    String address = scanner.next();

                    user = new User();
                    user.setAccountNumber(random.nextLong());
                    user.setName(name);
                    user.setAddress(address);
                    user.setAmount(0);
                    ApplicationMessage message = bankingApplicationUtility.createAccount(user);
                    if (message.isSuccess()){
                        user = message.getData();
                        System.out.println("Your account number is : ");
                        System.out.println(user.getAccountNumber());
                        System.out.println("Press 4 and add your account number to view more details");
                    }
                    else {
                        System.out.println(message.getErrorMessage());
                        System.out.println("sorry,error");
                    }

                    break;

                case 2:
                    bankingApplicationUtility.depositAmount(askAccountNumber(), askAmount());

                    break;

                case 3:


                   bankingApplicationUtility.withdrawAmount(askAccountNumber(), askAmount());




                    break;

                case 4:
                    System.out.println("Enter your Account Number");
                    Long accNo = scanner.nextLong();

                    user = bankingApplicationUtility.getAccountDetails(accNo);

                    if (user == null){
                        System.out.println("this account number doesnot exists");
                    }else {
                        System.out.println("Account Number :" + user.getAccountNumber());
                        System.out.println("Name : " + user.getName());
                        System.out.println("Address : " + user.getAddress());
                        System.out.println("Amouunt : " + user.getAmount());
                    }

                    break;

                case 5:
                   bankingApplicationUtility.closeAccount(askAccountNumber());
                   break;


                default:
                    System.out.println("Invalid Choice.!!");
                    System.exit(0);
                    break;

            }

        }

    }
}
