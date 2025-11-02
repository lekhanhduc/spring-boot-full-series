package vn.khanhduc.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.khanhduc.springboot.dto.request.UserCreateRequest;
import vn.khanhduc.springboot.dto.response.UserCreateResponse;
import vn.khanhduc.springboot.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }
}
