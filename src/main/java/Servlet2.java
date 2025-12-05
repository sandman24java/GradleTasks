import dto.NumberAdder;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="NumberAdder",
        urlPatterns = "/numadder",
        initParams = {
                @WebInitParam(name = "2nd", value = "2")
        }
)
public class Servlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        Integer my_rand_number1 = (Integer)ctx.getAttribute("newRandNum1");
        Integer my_rand_number2 = (Integer)ctx.getAttribute("newRandNum2");
        NumberAdder numberAdder = new NumberAdder();
        response.setStatus(200);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(numberAdder.add(my_rand_number1,my_rand_number2));
    }


}
