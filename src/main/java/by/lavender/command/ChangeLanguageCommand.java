package by.lavender.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    private static final String PARAM_LANGUAGE = "language";
    private static final String HEADER_ATTR = "referer";

    @Override
    public String execute(HttpServletRequest request) {

        String language = request.getParameter(PARAM_LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(PARAM_LANGUAGE, language);
        return request.getHeader(HEADER_ATTR);
    }
}
