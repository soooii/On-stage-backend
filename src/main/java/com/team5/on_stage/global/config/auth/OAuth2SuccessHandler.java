package com.team5.on_stage.global.config.auth;

import com.team5.on_stage.global.config.auth.dto.CustomOAuth2User;
import com.team5.on_stage.global.config.jwt.JwtUtil;
import com.team5.on_stage.global.config.auth.refresh.RefreshService;
import com.team5.on_stage.global.config.redis.RedisService;
import com.team5.on_stage.global.constants.ErrorCode;
import com.team5.on_stage.global.exception.GlobalException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.team5.on_stage.global.config.auth.cookie.CookieUtil.sendCookie;
import static com.team5.on_stage.global.config.jwt.JwtUtil.setErrorResponse;
import static com.team5.on_stage.global.constants.AuthConstants.DEPLOY_FRONT_DOMAIN;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public final String REDIRECT = DEPLOY_FRONT_DOMAIN + "/management";

    private final JwtUtil jwtUtil;
    private final RefreshService refreshService;
    private final RedisService redisService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        try {
            CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();

            String username = oauth2User.getUsername();
            String nickname = oauth2User.getNickname();
            String role = oauth2User.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElseThrow(() -> new GlobalException(ErrorCode.LOGIN_FAILED));

            String accessToken = jwtUtil.generateAccessToken(username, nickname, role);

            String refreshToken = refreshService.generateRefreshToken(username, nickname, role);

            redisService.setRefreshToken(refreshToken);

            sendCookie("access", accessToken, false, response);
            sendCookie("refresh", refreshToken, true, response);
            response.setStatus(HttpStatus.OK.value());

        } catch (Exception e) {
            setErrorResponse(response, ErrorCode.LOGIN_FAILED);
            throw new GlobalException(ErrorCode.LOGIN_FAILED);
        }

        response.sendRedirect(REDIRECT);
    }


}
