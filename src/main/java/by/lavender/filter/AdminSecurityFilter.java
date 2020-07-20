package by.lavender.filter;

import by.lavender.util.ResourceManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/admin/"},
        dispatcherTypes = {
                DispatcherType.REQUEST,
                DispatcherType.FORWARD
        })
public class AdminSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String roleName = (String) session.getAttribute("role");
        String loginPage = ResourceManager.getProperty("page.login");
        RequestDispatcher requestDispatcher;

        if (! "admin".equals(roleName)) {
            requestDispatcher = request.getServletContext().getRequestDispatcher(loginPage);
            requestDispatcher.forward(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }
}
