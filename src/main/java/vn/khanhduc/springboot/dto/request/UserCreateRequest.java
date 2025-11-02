package vn.khanhduc.springboot.dto.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String email;
    private String password;
}
