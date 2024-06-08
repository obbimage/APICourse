package com.app.course;

import com.app.course.models.User;
import com.app.course.repository.UserRepository;
import com.app.course.security.user.UserSecurityService;
import com.app.course.service.UserService;
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
            user.setPassword("12345678");

            userSecurityService.registerAdmin(user);
        }
    }
}
