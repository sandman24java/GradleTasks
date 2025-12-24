package task16_17_19.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import task16_17_19.dto.UserDetailsDto;

import java.io.IOException;
import java.util.Set;


@WebFilter(filterName = "AuthCookieFilter", urlPatterns = {"/*"})
public class AuthFilter extends HttpFilter {
    // публичные урлы, на которые можно заходить без авторизации
    private static final Set<String> PUBLIC_URLS = Set.of("/car/login/sessionbased", "/car/login/cookiebased");

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String requestPath = req.getServletPath();

        //COOKIE CHECK PART
        // если в куках есть user и он не пустой считаем, что пользователь залогинен
        boolean isAuthforCookies = false;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())
                        && !cookie.getValue().isBlank()) {
                    isAuthforCookies = true;
                    break;
                }
            }
        }
        //SESSION CHECK PART
        boolean isAuthforSession = false;
        // Получаем HTTP сессию, если ее нет то не  создается новая
        HttpSession session = req.getSession(false);

        // Достаём объект пользователя из сессии
        if (session != null) {
             UserDetailsDto userDetailsDto = (UserDetailsDto) session.getAttribute("user");
            // Проверка аутентифицирован ли пользователь
            if (userDetailsDto != null
                    && userDetailsDto.email().equals("mymail@gmail.com")) {
                isAuthforSession = true;
            }
        }
        //COMMON PART
        boolean isAuth = isAuthforSession || isAuthforCookies;
        // если пользователь пытается попасть на логин и он НЕ авторизован
        // просто пропускаем его к логин-сервлету
        if (PUBLIC_URLS.contains(requestPath) && !isAuth) {
            chain.doFilter(req, res);
            // return останавливает дальнейшее исполнение кода
            return;
            // если пользователь пытается попасть на логин, но уже авторизован —
            // повторный логин не нужен, просто сообщаем об этом
        } else if (PUBLIC_URLS.contains(requestPath) && isAuth) {
            res.setContentType("text/plain; charset=UTF-8");
            res.getWriter().println("Already authorized");
            return;
        }
        // если запрос не к логину и пользователь авторизован —
        // пропускаем его дальше к нужному сервлету
        if (isAuth) {
            chain.doFilter(req, res);
            return;
        }
        // если пользователь не авторизован и пытается попасть на защищенный ресурс —
        // запрещаем доступ
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}





