package by.lavender.controller;

import by.lavender.command.ActionCommand;
import by.lavender.command.ActionFactory;
import by.lavender.command.ErrorHandler;
import by.lavender.service.exception.ServiceException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class Controller extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand actionCommand = client.defineCommand(request);

        try {
            String page = actionCommand.execute(request);
            if(page.contains("?")) {
                response.sendRedirect(page);
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (ServiceException e) {
            ErrorHandler.handleError(request, response, e);
        }
    }
}
