import dto.RandomNumberProvider;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//Это указание Tomcat’у:
//«Этот Java-класс — сервлет, и его нужно подключить по URL /hello.»
//Классы сервлета синглтон его объект можно создать только один раз
@WebServlet(
        name = "RandNumGenServlet",
        urlPatterns = {"/randnum", "/myFirstOne"},
        initParams = {
                @WebInitParam(name = "1st", value = "1")
        }
)

public class Servlet1 extends HttpServlet {
    //CatalinaEngine делает сервлеты работоспособными
    @Override
    public void init() throws ServletException {
        // открывается только 1 раз при запуске
        System.out.println("Servlet1 init");
    }
    @Override
    public void destroy() {
        System.out.println("Servlet1 destroyed");
        // открывается только 1 раз при закрытии
    }

//    doGet() — это место, где ты пишешь, ЧТО отдавать пользователю при GET-запросе.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RandomNumberProvider randomNumberProvider = new RandomNumberProvider();
        ServletContext сtx = getServletContext();
        Integer randint1 = randomNumberProvider.generateNumber();
        Integer randint2 = randomNumberProvider.generateNumber();
        сtx.setAttribute("newRandNum1",randint1);
        сtx.setAttribute("newRandNum2",randint2);

        response.setStatus(200);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Generated "+ randint1 + " & "  + randint2 + " for Servlet2");
    }



}
