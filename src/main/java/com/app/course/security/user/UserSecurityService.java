package com.app.course.security.user;

import com.app.course.config.Constants;
import com.app.course.models.User;
import com.app.course.models.VerificationToken;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.UserRepository;
import com.app.course.repository.VerificationRepository;
import com.app.course.security.SecurityConfig;
import com.app.course.service.email.EmailService;
import com.app.course.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationRepository verificationRepository;
    @Autowired
    private EmailService emailService;
    @Value("${server.url}")
    private String url;

    private String getConfirmationUrl() {
        return url + "/register/confirm?token=";
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // kiểm tra user có tồn tại hay không
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserSecurity(user);
    }

    private ResponseEntity<RepositoryObject> register(User userRegister) {
        // username va email giong nhau
        String mail = userRegister.getUsername();
        if (userRepository.findByEmail(mail).isPresent()) {
            return Response.resultFail("Tài khoản đã được đăng ký");
        }
        // endcode passs
        String password = userRegister.getPassword();
        userRegister.setPassword(SecurityConfig.passwordEncoder().encode(password));
        // set mailUser
        userRegister.setEmail(mail);

        var userResponse = userRepository.save(userRegister);

        // create token for confirm account
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(userResponse);
        verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        // save token
        verificationRepository.save(verificationToken);

        // gửi token xác thực qua mail
        String urlConfirm = getConfirmationUrl() + token;
        String content = String.format("Click vào đường dẫn để xác nhận đăng ký tài khoản: %s> ", urlConfirm);
        // gửi mail và đưa ra lỗi khi gửi mail không thành công
        if (!emailService.sendEmail(mail, "Xác thực tài khoản", content)) {
            return Response.resultFail("Địa chỉ mail không chính xác");
        }

        return Response.resultOk(userResponse);
    }

    // xác thực người dùng
    public ResponseEntity<?> confirmUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");

        // check token
        Optional<VerificationToken> verificationTokenOptional = verificationRepository.findByToken(token);
        if (verificationTokenOptional.isEmpty()){
            String successHtml = "<html><body><h1>Token Không tồn tại!</h1></body></html>";
            return new ResponseEntity<>(successHtml, headers, HttpStatus.OK);
        }
//            return Response.resultFail("Token khong ton tai");

        VerificationToken verificationToken = verificationTokenOptional.get();
        // nếu token hết hạn thì tạo token mơi
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            // Tạo lại token mới
            String newToken = UUID.randomUUID().toString();
            verificationToken.setToken(newToken);
            verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
            verificationRepository.save(verificationToken);

            // Gửi lại email xác nhận với token mới
            String urlConfirm = getConfirmationUrl() + newToken;
            emailService.sendEmail(verificationToken.getUser().getEmail(), "Registration Confirmation", "Please confirm your registration by clicking on the link: " + urlConfirm);

//            return Response.resultFail("Token hết hạn, vui lòng kiểm tra lại email để nhận token mới");
            String successHtml = "<html><body><h1>Token hết hạn, vui lòng kiểm tra lại email để nhận token mới </h1></body></html>";
            return new ResponseEntity<>(successHtml,headers, HttpStatus.OK);
        }

        // Xác nhận người dùng nếu token hợp lệ
        User user = verificationToken.getUser();
        user.setEnabled(true);
        var response = userRepository.save(user);
        String username = user.getUsername();
        String successHtml =String.format("<html><body><h1> Kích hoạt tài khoản %s thành công </h1></body></html>",username);
        return new ResponseEntity<>(successHtml,headers, HttpStatus.OK);
    }

    public ResponseEntity<RepositoryObject> registerEducator(User user) {
        user.createEducator();
        user.setRole(Constants.ROLE_EDUCATOR);
        return register(user);
    }

    public ResponseEntity<RepositoryObject> registerUser(User user) {
        user.createUser();
        user.setRole(Constants.ROLE_USER);
        return register(user);
    }

    public ResponseEntity<RepositoryObject> registerAdmin(User user) {
        user.setRole(Constants.ROLE_ADMIN);
        return register(user);
    }
}
