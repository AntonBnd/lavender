package by.lavender.command;

import by.lavender.command.check.LoginVerification;
import by.lavender.beans.User;
import by.lavender.service.UserService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.UserServiceImpl;
import by.lavender.util.PasswordHash;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_USER = "user";
    private static final String ATTR_WARNING = "warning";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String login = request.getParameter(PARAM_USERNAME);
        String password = request.getParameter(PARAM_PASSWORD);
        String warn = LoginVerification.checkLoginData(session, login, password);
        String loginPage = ResourceManager.getProperty("page.login");
        String mainPage = ResourceManager.getProperty("page.index");

        if(warn != null) {
            request.setAttribute(ATTR_WARNING, warn);
            return loginPage;
        }
        password = PasswordHash.SHAHashing(password);
        User user = service.getByLoginPassword(login, password);

        if (user != null) {
            session.setAttribute(SESSION_ROLE, user.getRole().getRoleName());
            session.setAttribute(SESSION_USER, user);
        } else {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.authorization"));
            return loginPage;
        }
        return mainPage;
    }

}
