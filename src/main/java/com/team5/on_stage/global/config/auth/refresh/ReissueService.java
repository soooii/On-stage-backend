package com.team5.on_stage.global.config.auth.refresh;

import com.team5.on_stage.global.config.jwt.JwtUtil;
import com.team5.on_stage.global.config.redis.RedisService;
import com.team5.on_stage.global.constants.ErrorCode;
import com.team5.on_stage.global.exception.GlobalException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.team5.on_stage.global.config.auth.cookie.CookieUtil.*;
import static com.team5.on_stage.global.constants.AuthConstants.*;
import static com.team5.on_stage.global.config.jwt.JwtUtil.setErrorResponse;

@RequiredArgsConstructor
@Service
public class ReissueService {

    private final JwtUtil jwtUtil;
    private final RedisService redisService;
    private final RefreshService refreshService;

    public void reissueRefreshToken(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {

        /* Refresh Token 검증 */

        String oldRefreshToken = getStringValueFromCookies(request, "refresh");

        try {
            if (oldRefreshToken == null) {
                throw new GlobalException(ErrorCode.INVALID_REFRESH_TOKEN);
            }

            if (jwtUtil.isExpired(oldRefreshToken)) {
                throw new GlobalException(ErrorCode.REFRESH_TOKEN_EXPIRED);
            }

            String tokenType = jwtUtil.getClaim(oldRefreshToken, "type");

            if (!tokenType.equals("refresh")) {
                throw new GlobalException(ErrorCode.TYPE_NOT_MATCHED);
            }

            if (redisService.getRefreshToken(oldRefreshToken) == null) {
                throw new GlobalException(ErrorCode.REFRESH_TOKEN_NOT_EXISTS);
            }


        } catch (GlobalException e) {
            setErrorResponse(response, ErrorCode.FAILED_TO_REISSUE);
            throw new GlobalException(ErrorCode.FAILED_TO_REISSUE);
        }

        /* Refresh, Access Token 재발급 */

        String username = jwtUtil.getClaim(oldRefreshToken, "username");
        String nickname = jwtUtil.getClaim(oldRefreshToken, "nickname");
        String role = jwtUtil.getClaim(oldRefreshToken, "role");

        String newAccessToken = jwtUtil.generateAccessToken(username, nickname, role);
        String newRefreshToken = refreshService.generateRefreshToken(username, nickname, role);


        redisService.deleteRefreshToken(oldRefreshToken);
        redisService.setRefreshToken(newRefreshToken, username);

        deleteCookie("refresh", response);
        deleteCookie("access", response);
        deleteCookie("JSESSIONID", response);

        response.setHeader(AUTH_HEADER, AUTH_TYPE + newAccessToken);

        sendCookie("refresh", newRefreshToken, true, response);
        sendCookie("access", newAccessToken, false, response);
    }
}
