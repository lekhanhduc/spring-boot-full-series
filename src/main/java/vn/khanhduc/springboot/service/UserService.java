package vn.khanhduc.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.khanhduc.springboot.dto.request.UserCreateRequest;
import vn.khanhduc.springboot.dto.response.UserCreateResponse;
import vn.khanhduc.springboot.model.User;
import vn.khanhduc.springboot.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponse createUser(UserCreateRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exist");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return UserCreateResponse.builder()
                .email(user.getEmail())
                .build();
    }

}
