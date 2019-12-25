package com.bank.test.Utility;


/*
This calss is used for savinf User objet to file.
Deals with File I/O operations


*/


import com.bank.test.Model.ApplicationMessage;
import com.bank.test.Model.User;

import java.io.*;

public class UserCrudFileUtility implements UserCrudUtility {

    private  String dbPath;

    public UserCrudFileUtility(){
        dbPath = "C:\\Users\\Arpan Acharya\\IdeaProjects\\bank documents\\";
    }
    @Override
    public ApplicationMessage saveUserDetails(User user) {


            ApplicationMessage applicationMessage = new ApplicationMessage();
            try {
                FileWriter fileWriter = new FileWriter(dbPath + user.getAccountNumber());
                fileWriter.write("Account=" + user.getAccountNumber() + "\n");
                fileWriter.write("Name=" + user.getName() + "\n");
                fileWriter.write("Address=" + user.getAddress() + "\n");
                fileWriter.write("Balance=" + user.getAmount() + "\n");

                fileWriter.close();
                //success
                applicationMessage.setSuccess(true);
                applicationMessage.setErrorMessage(null);
                applicationMessage.setData(null);

            } catch (IOException e) {
                applicationMessage.setSuccess(false);
                applicationMessage.setErrorMessage(e.getMessage());
            }
            return applicationMessage;

    }

    @Override
    public User getUserDetails(Long accountNumber) {

            User user = null;

            try {
                FileReader fileReader = new FileReader(dbPath + accountNumber);


                BufferedReader reader = new BufferedReader(fileReader);
                String line = null;
                user = new User();


                while ((line = reader.readLine()) != null) {


                    String lines[] = line.split("=");


                    // .equals le chai string compare garxa

                    if (lines[0].equalsIgnoreCase("Account")) {

                        //parsing String into long by using wrapper function

                        user.setAccountNumber(Long.parseLong(lines[1]));
                    } else if (lines[0].equalsIgnoreCase("Name")) {
                        user.setName(lines[1]);

                    } else if (lines[0].equalsIgnoreCase("Address")) {
                        user.setAddress(lines[1]);

                    } else if (lines[0].equalsIgnoreCase("Balance")) {

                        //parsing String into double by using wrapper function

                        user.setAmount(Double.parseDouble(lines[1]));

                    }


                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;


        }



    @Override
    public void deleteUser(User user) {

    }
}
