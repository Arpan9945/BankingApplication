package com.bank.test.Utility;

import com.bank.test.Model.ApplicationMessage;
import com.bank.test.Model.User;

import java.sql.*;

public class UserCrudDbUtility implements UserCrudUtility {
    @Override
    public ApplicationMessage saveUserDetails(User user) {

        ApplicationMessage appMessage = new ApplicationMessage();

        if(user.getId() == 0){
            return insertUser(user);
        } else {
            return  updateUser(user);
        }


    }

    @Override
    public User getUserDetails(Long accountNumber) {
        //1.open Db connection to mysql
        //2. Hold connection using connection object
        //3. Prepare query using this account number i.e use select query
        //4. Hold result set object
        //5. Iterate through resultset object using its next() function
        //6. Set values to user object
        //7. Return user object

        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "CLFA5AD7CB";
            Connection connection = DriverManager.getConnection(url, userName, password);
            String selectQuery = "select * from tbl_user\n" +
                    "where\n" +
                    "account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, accountNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            user = new User();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Long accNum = resultSet.getLong("account_number");
                Double amount = resultSet.getDouble("amount");

                //setting local variables values to user attributes.
                user.setId(id);
                user.setName(name);
                user.setAddress(address);
                user.setAccountNumber(accNum);
                user.setAmount(amount);
            }
            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    @Override
    public void deleteUser(User user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "CLFA5AD7CB";
            Connection connection = DriverManager.getConnection(url, userName, password);

            String deleteQuery = "delete from tbl_user where id= ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();
            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ApplicationMessage updateUser(User user){

        ApplicationMessage appMessage = new ApplicationMessage();

        try {
            Class.forName("com.mysql.jdbc.Driver");


            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "CLFA5AD7CB";
            Connection connection = DriverManager.getConnection(url, userName, password);

            String updateQuery = "update tbl_user\n" +
                    "set\n"+
                    "amount=?\n"+
                    "where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, user.getAmount());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.execute();

            appMessage.setSuccess(true);
            appMessage.setErrorMessage(null);
            appMessage.setData(user);
            connection.close();

        } catch (ClassNotFoundException e) {
            appMessage.setSuccess(false);
            appMessage.setErrorMessage(e.getMessage());
            appMessage.setData(null);
            e.printStackTrace();
        } catch (SQLException e) {
            appMessage.setSuccess(false);
            appMessage.setErrorMessage(e.getMessage());
            appMessage.setData(null);
            e.printStackTrace();
        }
        return appMessage;


    }
    public ApplicationMessage insertUser(User user){

        ApplicationMessage appMessage = new ApplicationMessage();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "CLFA5AD7CB";
            Connection connection = DriverManager.getConnection(url, userName, password);

            String insertQuery = "insert into tbl_user\n" +
                    "(account_number, name, address, amount)\n" +
                    "values\n" +
                    "(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setLong(1, user.getAccountNumber());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setDouble(4, user.getAmount());

            boolean flag = preparedStatement.execute();

            appMessage.setSuccess(true);
            appMessage.setErrorMessage(null);
            appMessage.setData(user);

            connection.close();


        } catch (ClassNotFoundException e) {
            appMessage.setSuccess(false);
            appMessage.setErrorMessage(e.getMessage());
            appMessage.setData(null);

            e.printStackTrace();
        } catch (SQLException e) {
            appMessage.setSuccess(false);
            appMessage.setErrorMessage(e.getMessage());
            appMessage.setData(null);
            e.printStackTrace();
        }
        return appMessage ;

    }
}
