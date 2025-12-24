package task16_17_19.controller;
import jakarta.servlet.annotation.WebServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import task16_17_19.dto.LoginDto;
import task16_17_19.dto.UserDetailsDto;
import task16_17_19.service.AuthService;
import task16_17_19.service.AuthServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="login",urlPatterns =
        {"/car/login/cookiebased","/car/login/sessionbased","/car/login/tokenbased"})
public class LoginController extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        response.setContentType("application/json");
        try (BufferedReader buffReader = request.getReader()) {
            StringBuffer myString = new StringBuffer();
            String line = null;
            while ((line = buffReader.readLine()) != null) {
                myString.append(line);
            }
            LoginDto loginDto = OBJECT_MAPPER.readValue(myString.toString(), LoginDto.class);
            boolean isValid = authService.authenticate(loginDto);
            switch (path) {
                case "/car/login/cookiebased":
                    if (isValid) {
                        Cookie userCookie = new Cookie("user", loginDto.username());
                        userCookie.setHttpOnly(true);
                        userCookie.setMaxAge(3600);
                        Cookie userChartCookie = new Cookie("userChart", "items");
                        userCookie.setPath("/");
                        userChartCookie.setPath("/");
                        userChartCookie.setMaxAge(3600);
                        response.addCookie(userCookie);
                        response.addCookie(userChartCookie);
                        response.setStatus(200);

                    } else {
                        response.setStatus(401);
                    }
                    break;
                case "/car/login/sessionbased":
                    if (isValid) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute
                                        ("user",
                                                new UserDetailsDto(
                                                        "mymail@gmail.com","Murad","Mamedov"));
                        session.setMaxInactiveInterval(3600);
                        response.setStatus(200);
                    }else{
                        response.setStatus(401);
                    }
                    break;
                    case "/car/login/tokenbased":
                        if (isValid) {

                        }
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown endpoint: " + path);
            }
        }
    }
}
