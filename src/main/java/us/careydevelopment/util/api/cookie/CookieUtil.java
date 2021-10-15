package us.careydevelopment.util.api.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {

    public static void expireCookie(String cookieName, HttpServletResponse response) {
        final Cookie cookie = new Cookie(cookieName, "");

        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);

        response.addCookie(cookie);
    }
}
