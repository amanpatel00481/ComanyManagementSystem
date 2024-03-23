package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.services.RoleServiceimpl;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private RoleServiceimpl roleService;

    public AuthController(UserService userService, RoleServiceimpl roleService) {
        this.userService = userService;
        this.roleService= roleService;
    }

   
    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/role")
    public String raoleManagement(){
        return "roles";
    }
    
    @GetMapping("/welcome-home")
    public String welcomeHome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return "welcome-home";
        } else {
            return "redirect:/login";
        }
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    
    
    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
   

//  public RoleController(RoleServiceimpl roleService) {
//      this.roleService = roleService;
//  }

  // handler method to show the list of roles
  @GetMapping("/roles")
  public String roles() {
//      List<RoleDto> roles = roleService.getAllRoles();
//      model.addAttribute("roles", roles);
      return "roles";
  }

  // handler method to show the role creation form
  @GetMapping("/roles/create")
  public String showRoleCreationForm(Model model) {
      RoleDto role = new RoleDto();
      model.addAttribute("role", role);
      return "create-role";
  }

  // handler method to handle role creation form submit request
  @PostMapping("/roles/create")
  public String createRole(@Valid @ModelAttribute("role") RoleDto roleDto,
                           BindingResult result,
                           Model model) {
      if (result.hasErrors()) {
          return "create-role";
      }

      roleService.createRole(roleDto);
      return "redirect:/roles";
  }
    
    
}
