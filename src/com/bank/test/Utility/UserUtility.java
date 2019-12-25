package com.bank.test.Utility;

import com.bank.test.Model.ApplicationMessage;
import com.bank.test.Model.User;

import java.io.*;

public class UserUtility {
    private String dbPath;

    public UserUtility(){
        dbPath = "C:\\Users\\Arpan Acharya\\IdeaProjects\\bank documents\\";

    }


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

        } catch (IOException e) {
            applicationMessage.setSuccess(false);
            applicationMessage.setErrorMessage(e.getMessage());
        }
        return applicationMessage;


    }

    public User getUserDetails(long accountNumber) {
        // 1. Open File Related to this code
        // 2. Read File
        // 3. Parse the current line
        // 4. Set the value to the user object
        // 5. return user object
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



}
