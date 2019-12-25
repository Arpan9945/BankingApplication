package com.bank.test.Utility;

import com.bank.test.Model.ApplicationMessage;
import com.bank.test.Model.User;

public class BankingApplicationUtility {

    private UserCrudUtility userCrudUtility;

    public BankingApplicationUtility(){
        userCrudUtility = new UserCrudDbUtility();
    }

    public ApplicationMessage createAccount(User user){
        return userCrudUtility.saveUserDetails(user);

    }
    public void withdrawAmount(Long accountNumber , Double withdrawAmount){
        User fetchedUser = userCrudUtility.getUserDetails(accountNumber);

        if (fetchedUser == null) {
            System.out.println("Sorry this account number doesn't exist in our system.");
        } else {


            if(fetchedUser.getAmount() < withdrawAmount) {
                System.out.println("You don't have enough balance in your account");
            }else {

                fetchedUser.setAmount(fetchedUser.getAmount() - withdrawAmount);

                ApplicationMessage message = userCrudUtility.saveUserDetails(fetchedUser);

                if(message.isSuccess()){
                    System.out.println("Your Details is successfully saved!");
                } else {
                    System.out.println(message.getErrorMessage());
                    System.out.println("Error, Please try again!");
                }
            }

        }

    }
    public void depositAmount(Long accountNumber, Double depositeAmount){


        User fetchedUser = userCrudUtility.getUserDetails(accountNumber);

        if (fetchedUser == null) {
            System.out.println("Sorry this account number doesn't exist in our system.");
        } else {

            fetchedUser.setAmount(fetchedUser.getAmount() + depositeAmount);

            ApplicationMessage message = userCrudUtility.saveUserDetails(fetchedUser);

            if(message.isSuccess()){
                System.out.println("Your Details is successfully saved!");
            } else {
                System.out.println(message.getErrorMessage());
                System.out.println("Error, Please try again!");
            }

        }

    }
    public User getAccountDetails(Long accountNumber) {
        return userCrudUtility.getUserDetails(accountNumber);

    }
    public void closeAccount(Long accountNumber){
        User user = getAccountDetails(accountNumber);
        if (user == null){
            System.out.println("this acc num doesnot exists");
        }
        else{
            userCrudUtility.deleteUser(user);
        }


    }
}
