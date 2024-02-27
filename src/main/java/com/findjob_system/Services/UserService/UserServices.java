package com.findjob_system.Services.UserService;

import com.findjob_system.models.User;
import com.findjob_system.models.UserOPT;

public interface UserServices {

    Boolean saveUser(User user);

    Boolean deteleUser(User user);

    Boolean userUninscruption(User user);

    Boolean userAccountActivation(User user);

    Boolean userPasswordUpdate(User user);

    User findUserOpt(int useropt);
    User checkUserExistence(User email);
}
