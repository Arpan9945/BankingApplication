package com.bank.test.Utility;

import com.bank.test.Model.ApplicationMessage;
import com.bank.test.Model.User;

public interface UserCrudUtility {
    ApplicationMessage saveUserDetails (User user);
    User getUserDetails (Long accountNumber);
    void deleteUser (User user);

}
