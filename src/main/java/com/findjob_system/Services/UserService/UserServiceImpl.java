package com.findjob_system.Services.UserService;

import com.findjob_system.Exceptions.NotSuchOpt;
import com.findjob_system.Exceptions.UserAlreadyExistsException;
import com.findjob_system.Exceptions.UserNotFoundException;
import com.findjob_system.Services.EmailService.EmailServiceImp;
import com.findjob_system.models.Confirmation;
import com.findjob_system.models.Role;
import com.findjob_system.models.User;
import com.findjob_system.models.UserOPT;
import com.findjob_system.repository.ConfirmationRepository;
import com.findjob_system.repository.RoleRepository;
import com.findjob_system.repository.UserOPTRepository;
import com.findjob_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailServiceImp emailServiceImp;
    private final ConfirmationRepository confirmationRepository;
    private final UserOPTRepository userOPTRepository;
    @Override
    public Boolean saveUser(User user) {
        Optional<User> searchUser = userRepository.findByUserEmail(user.getUserEmail());
        if(searchUser.isPresent()) {
            throw new UserAlreadyExistsException("A user with email " + searchUser.get().getUserEmail() + " already exists");
        }
        Role role = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Confirmation confirmation = new Confirmation(user);

        emailServiceImp.confirmAccountEmail(user.getUserName(),user.getUserEmail(),confirmation.getToken());
        user.setEnabled(false);
        userRepository.save(user);
        confirmationRepository.save(confirmation);
        log.info("user has been saved successfully here is the confirmation details "+ confirmation.getToken());
        return Boolean.TRUE;
    }

    @Override
    public Boolean deteleUser(User user) {
        return null;
    }

    @Override
    public Boolean userUninscruption(User user) {
        return null;
    }

    @Override
    public Boolean userAccountActivation(User user) {
        return null;
    }

    @Override
    @Transactional
    public Boolean userPasswordUpdate(User user) {
         userRepository.updateUserPassword(user.getUserId(), user.getPassword());
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public User findUserOpt(int useropt){
       Optional<UserOPT> user = userOPTRepository.findByUserOpt(useropt);
       if (!(user.isPresent())) {
           throw new NotSuchOpt("please provide the correct opt code for user: "+ user.get().getUser().getUserEmail());
       }
       Optional<User> userNow = userRepository.findByUserId(user.get().getUser().getUserId());
//       System.out.println("the opt has been found " + usercode.get().getUserOpt() + " for user "+ usercode.get().getUser().getUserEmail());
        return userNow.get();
    }

    @Override
    @Transactional
    public User checkUserExistence(User usercheck) {
        Optional<User> user = userRepository.findByUserEmail(usercheck.getUserEmail());
        if (!(user.isPresent())) {
            throw new UserNotFoundException("User " + usercheck.getUserEmail() + " does not exist");
        }
        UserOPT userOpt = new UserOPT(user.get());
        userOPTRepository.save(userOpt);
        emailServiceImp.sendOPtCode(user.get().getUserName(),user.get().getUserEmail(),userOpt.getUserOpt());
        return user.get();
    }
}
