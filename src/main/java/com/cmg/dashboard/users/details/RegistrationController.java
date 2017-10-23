package com.cmg.dashboard.users.details;

import com.cmg.dashboard.users.UserRegistrator;
import com.cmg.dashboard.users.details.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping
    public List<String> getAvailableRoles(){
        return userDetailsService.getAvailableRolesAsDisplayNames();
    }

    @PostMapping
    public String createNewUser(@RequestBody UserRegistrator userRegistrator){
        userDetailsService.registerUser(userRegistrator);
        return "User " + userRegistrator.getEmail() + " created successfully";
    }
}
