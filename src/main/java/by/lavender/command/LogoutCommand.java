package by.lavender.command;

import by.lavender.service.exception.ServiceException;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String mainPage = ResourceManager.getProperty("page.index");
        request.getSession().invalidate();
        return mainPage;
    }
}
