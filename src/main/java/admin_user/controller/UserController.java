package admin_user.controller;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import admin_user.dto.UserDto;
import admin_user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jeduler/pp")
public class UserController {
	private final UserDetailsService userDetailsService;
	private final UserService userService;
	
//	@GetMapping("/registration")
//	public String getRegistrationPage() {
//		return "register";
//	}
	
//	@PostMapping("/registration")
//	public String saveUser(@RequestBody UserDto userDto) {
//		userService.save(userDto);
//		model.addAttribute("message", "Registered Successfuly!");
//		return "register";
//	}
	
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
	
	@GetMapping("/user-page")
	public String userPage (/*Model model, Principal principal*/) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//		model.addAttribute("user", userDetails);
		return "user";
	}
	
	@GetMapping("/admin-page")
	public String adminPage (/*Model model, Principal principal*/) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//		model.addAttribute("user", userDetails);
		return "admin";
	}

}
