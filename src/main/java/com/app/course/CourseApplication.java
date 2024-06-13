package com.app.course;

import com.app.course.models.User;
import com.app.course.repository.UserRepository;
import com.app.course.security.SecurityConfig;
import com.app.course.security.user.UserSecurityService;
import com.app.course.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    UserSecurityService userSecurityService;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        boolean existAdmin = userRepository.existsByUsername("admin");
        if (!existAdmin) {
            User user = new User();
            user.setUsername("admin");
            user.setRole("ADMIN");
            user.setEnabled(true);
            user.setPassword(SecurityConfig.passwordEncoder().encode("12345678"));
            userRepository.save(user);
        }
    }
}
