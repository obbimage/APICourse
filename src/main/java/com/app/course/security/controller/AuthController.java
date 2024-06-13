package com.app.course.security.controller;

import com.app.course.config.AlertQuery;
import com.app.course.config.Constants;
import com.app.course.models.User;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import com.app.course.repository.UserRepository;
import com.app.course.security.PayloadLogin;
import com.app.course.security.jwt.JwtAuthenticationFilter;
import com.app.course.security.jwt.JwtTokenProvider;
import com.app.course.security.user.UserSecurity;
import com.app.course.security.user.UserSecurityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private UserRepository userRepository;
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }




    public ResponseEntity<?> login(LoginRequest loginRequest, String role, HttpServletRequest request) {

        try {
            /* ============= login with user and password if login with jwt failed================*/
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username,
                            loginRequest.password
                    )
            );

            // check if the user enable
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if(!userDetails.isEnabled()){
                return Response.resultFail("Tài khoản chưa được kích hoạt");
            }

            // Kiểm tra xem vai trò của người dùng có hợp lệ không
            if (!isValidRole(authentication, role)) {
                // Nếu không, trả về lỗi 403 Forbidden
                return Response.result(HttpStatus.FORBIDDEN, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // chuyền thành mã jwt
            String jwt = jwtTokenProvider.generateToken((UserSecurity) authentication.getPrincipal());
            // data trả về cho client
            PayloadLogin payloadLogin = new PayloadLogin(((UserSecurity) authentication.getPrincipal()).getUser(), jwt);

            return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, payloadLogin);
        } catch (AuthenticationException e) {
            return Response.result(HttpStatus.FORBIDDEN, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
        }
    }

    @GetMapping("/infoUser")
    public ResponseEntity<?> getUser(@RequestBody String jwt){
        try {
            UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long userId = userSecurity.getUser().getId();

            Optional<User> userOptional = userRepository.findById(userId);

            User user = userOptional.get();
            return Response.resultOk(user);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return login(loginRequest, Constants.ROLE_ADMIN, request);
    }

    @PostMapping("/educator")
    public ResponseEntity<?> educator(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return login(loginRequest, Constants.ROLE_EDUCATOR, request);
    }


    @PostMapping("/user")
    public ResponseEntity<?> user(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return login(loginRequest, Constants.ROLE_USER, request);
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
