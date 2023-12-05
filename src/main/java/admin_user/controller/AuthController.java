package admin_user.controller;

import admin_user.requests.AuthenticationRequest;
import admin_user.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jeduler/auth")
public class AuthController {
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<?> auth(
            @RequestBody AuthenticationRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        System.out.println("Here");

        return ResponseEntity.ok(
                service.authenticate(
                        loginRequest,
                        request,
                        response
                )
        );
    }
}
