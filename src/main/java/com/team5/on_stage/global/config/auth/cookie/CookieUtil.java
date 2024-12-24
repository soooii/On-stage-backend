package com.team5.on_stage.global.config.auth.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.team5.on_stage.global.constants.AuthConstants.DEPLOY_DOMAIN;

public class CookieUtil {

    public final static String COOKIE_DOMAIN = DEPLOY_DOMAIN; //"localhost";
    public final static String COOKIE_PATH = "/";
    public final static int COOKIE_MAX_AGE = 24 * 60 * 60;


    public static void sendCookie(String key,
                                  String value,
                                  Boolean httpOnly,
                                  HttpServletResponse response) {

        Cookie cookie = new Cookie(key, value);

        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath(COOKIE_PATH);
        cookie.setSecure(true);
        cookie.setHttpOnly(httpOnly);

        response.addCookie(cookie);
    }

    public static void deleteCookie(String key,
                                    HttpServletResponse response) {

        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath(COOKIE_PATH);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }

    public static String getStringValueFromCookies(HttpServletRequest request,
                                                   String key) {

        Cookie[] cookies = request.getCookies();
        String value = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                value = cookie.getValue();
            }
        }

        return value;
    }
}
