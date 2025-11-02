package vn.khanhduc.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.khanhduc.springboot.dto.JwtInfo;
import vn.khanhduc.springboot.dto.TokenPayload;
import vn.khanhduc.springboot.dto.request.LoginRequest;
import vn.khanhduc.springboot.dto.response.LoginResponse;
import vn.khanhduc.springboot.model.RedisToken;
import vn.khanhduc.springboot.model.User;
import vn.khanhduc.springboot.repository.RedisTokenRepository;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RedisTokenRepository redisTokenRepository;

    public LoginResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        User user = (User) authenticate.getPrincipal();

        TokenPayload accessPayload = jwtService.generateAccessToken(user);
        TokenPayload refreshPayload = jwtService.generateRefreshToken(user);

        redisTokenRepository.save(RedisToken.builder()
                        .jwtId(refreshPayload.getJwtId())
                        .expiredTime(refreshPayload.getExpiredTime().getTime())
                .build());

        return LoginResponse.builder()
                .accessToken(accessPayload.getToken())
                .refreshToken(refreshPayload.getToken())
                .build();
    }

    public void logout(String token) throws ParseException {
        JwtInfo jwtInfo = jwtService.parseToken(token);
        String jwtId = jwtInfo.getJwtId();
        Date issueTime = jwtInfo.getIssueTime();
        Date expiredTime = jwtInfo.getExpiredTime();
        if(expiredTime.before(new Date())){
            return;
        }

        RedisToken redisToken = RedisToken.builder()
                .jwtId(jwtId)
                .expiredTime(expiredTime.getTime() - issueTime.getTime())
                .build();

        redisTokenRepository.save(redisToken);
        log.info("Logout success");
    }

}
