package com.findjob_system.resources;

import com.findjob_system.Services.UserService.UserServiceImpl;
import com.findjob_system.models.Dto.UserDTO;
import com.findjob_system.models.User;
import com.findjob_system.models.UserOPT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/operation_for_all")
@RequiredArgsConstructor
public class OperationForAll {

    private final UserServiceImpl userService;


    @GetMapping("/forgotpassword")
    public String forgotPassword(Model model){
        model.addAttribute("user", new User());
        return "forgotpassword";
    }
    @GetMapping("/searchemail")
    public String checkEmailExistance(@ModelAttribute("user") User user, Model model){
        Optional<User> userExistence = Optional.ofNullable(userService.checkUserExistence(user));
        userExistence.ifPresent(value -> model.addAttribute("user", value));
        model.addAttribute("useropt", new UserOPT());
        model.addAttribute("userExistance", "user does not exist");
        return "opt";
    }

    @GetMapping("/optCheck")
    public String checkOptExistance(@ModelAttribute("useropt") UserOPT userOPT, Model model){
        User optExistenceForUser = userService.findUserOpt(userOPT.getUserOpt());
        model.addAttribute("user", optExistenceForUser);
        model.addAttribute("userExistance", "user does not exist");
        return "renew";
    }

    @PostMapping("/renewpassword")
    public String renewUserPassword(@ModelAttribute("user") UserDTO userDTO, Model model){
        User user = new User(userDTO.getPassword());
        userService.userPasswordUpdate(user);
        model.addAttribute("user", new User());
        model.addAttribute("userExistance", "user does not exist");
        return "login";
    }



}




