package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    public static void sendAuthenticationCookie(HttpServletResponse response,
            String key, String value
    ) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 3600);
        response.addCookie(cookie);
    }

    public static void removeAuthenticationCookie(HttpServletResponse response,
            String key
    ) {
        Cookie cookie = new Cookie(key, "");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }
}
