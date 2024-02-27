package com.findjob_system.resources;


import com.findjob_system.Services.UserService.UserServiceImpl;
import com.findjob_system.Services.UserService.UserServices;
import com.findjob_system.models.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/jobseeker")
@RequiredArgsConstructor
public class JobSeekerResources {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/form")
    public String getJobseekerForm(@RequestParam(name = "role", required = false) String role,
                                   @RequestParam(name = "success", required = false)String success,
                                   @RequestParam(name = "error", required = false)String error,
                                   Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("role","ROLE_JOB_SEEKER");
        return "login";
    }

    @PostMapping("/registration")
    public String userRegistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        System.out.println("here the data :" +user.getUserEmail());
        try {
            //checking validation error
            if (result.hasErrors()) {
                model.addAttribute("user", new User());
                model.addAttribute("userlog", new User());
                return "redirect:/api/jobseeker/form?error=Please+check+your+input+data";
            }
            userServiceImpl.saveUser(user);
            return "redirect:/api/jobseeker/form";
        }catch (Exception e){
             System.out.println("the error in the catch "+ e);
        }
        return "redirect:/api/launchWeb?server=server+error!";
    }

}
