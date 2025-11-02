package vn.khanhduc.springboot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreateResponse {
    private String email;
}
