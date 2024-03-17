package com.app.course.security.user;

import com.app.course.models.User;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.UserRepository;
import com.app.course.security.SecurityConfig;
import com.app.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username){
        // kiểm tra user có tồn tại hay không
        User user = repository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserSecurity(user);
    }

    public ResponseEntity<RepositoryObject> register(User userRegister){
        // endcode passs
        String password = userRegister.getPassword();
        userRegister.setPassword(SecurityConfig.passwordEncoder().encode(password));

        return userService.insertUser(userRegister);
    }
}