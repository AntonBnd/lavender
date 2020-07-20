package by.lavender.command;

import by.lavender.command.check.RegistrationCheck;
import by.lavender.beans.User;
import by.lavender.service.UserService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.UserServiceImpl;
import by.lavender.util.PasswordHash;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrateCommand implements ActionCommand {
    private static final String PARAM_FULL_NAME = "fullname";
    private static final String PARAM_LAST_NAME = "lastname";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_PHONE_NUMBER = "phoneNumber";
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_USER = "user";
    private static final String ATTR_WARNING = "warning";
    private static final String COMMAND = "getLastMinuteTours";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        String firstName = request.getParameter(PARAM_FULL_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String login = request.getParameter(PARAM_USERNAME);
        String password = request.getParameter(PARAM_PASSWORD);
        String telNumber = request.getParameter(PARAM_PHONE_NUMBER);
        String warn = RegistrationCheck.checkRegistrationData(session, firstName,
                lastName, telNumber, login, password);
        if(warn != null) {
            request.setAttribute(ATTR_WARNING, warn);
            return ResourceManager.getProperty("page.registration");
        }
        password = PasswordHash.SHAHashing(password);
        User user = new User(firstName, lastName, password, login, telNumber);
        page = authenticate(user, session, request);

        return page;
    }

    private String authenticate(User user, HttpSession session, HttpServletRequest request) throws ServiceException {
        UserService service = UserServiceImpl.getInstance();
        String mainPage = ResourceManager.getProperty("page.index");

        if(service.addUser(user)) {
            session.setAttribute(SESSION_ROLE, service.getByLoginPassword(user.getLogin(), user.getPassword())
                    .getRole()
                    .getRoleName());
            session.setAttribute(SESSION_USER, user);
            return mainPage;
        } else {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.registration"));
            return ResourceManager.getProperty("page.registration");
        }
    }
}
