package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName="FilterOf1",urlPatterns={"/randnum","/myFirstOne"})
public class FilterForServlet1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String method = httpReq.getMethod();
        String uri = httpReq.getRequestURI();
        PrintWriter out = response.getWriter();
        out.println("Request " + method + " " + uri);
        chain.doFilter(request,response);
    }
}
