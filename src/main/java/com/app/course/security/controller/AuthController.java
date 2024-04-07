package com.app.course.security.controller;

import com.app.course.config.AlertQuery;
import com.app.course.config.Constants;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import com.app.course.security.jwt.JwtTokenProvider;
import com.app.course.security.user.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public ResponseEntity<?> login(LoginRequest loginRequest, String role) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username,
                            loginRequest.password
                    )
            );

            // Kiểm tra xem vai trò của người dùng có hợp lệ không
            if (!isValidRole(authentication, role)) {
                // Nếu không, trả về lỗi 403 Forbidden
                return Response.result(HttpStatus.FORBIDDEN, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
            // ...
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateToken((UserSecurity) authentication.getPrincipal());
            return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, jwt);
        } catch (AuthenticationException e) {
            return Response.result(HttpStatus.FORBIDDEN, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/educator")
    public ResponseEntity<?> educator(@RequestBody LoginRequest loginRequest) {
        return login(loginRequest, Constants.ROLE_EDUCATOR);
//        return Response.result(HttpStatus.OK,Status.FAILED,AlertQuery.CANT_NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<?> user(@RequestBody LoginRequest loginRequest) {
        return login(loginRequest, Constants.ROLE_USER);
    }


    @PostMapping("/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Logout successful";
    }

    private boolean isValidRole(Authentication authentication, String role) {
        // Kiểm tra nếu người dùng có vai trò mong muốn (ví dụ: ROLE_USER) thì trả về true, ngược lại trả về false
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(roleUser -> roleUser.equals(role));
    }

    public record LoginRequest(String username, String password) {
    }
}
