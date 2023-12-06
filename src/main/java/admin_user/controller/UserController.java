package admin_user.controller;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jeduler/user")
public class UserController {
	private final UserDetailsService userDetailsService;

	@GetMapping
	public UserDetails userPage (Principal principal) {
		return userDetailsService.loadUserByUsername(principal.getName());
	}
}
