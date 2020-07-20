package by.lavender.command;

import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpSession;

public class ServerMessage {
    private static final String ATTR_LANGUAGE = "language";

    public static String message(HttpSession session, String messageKey) {
        String language = (String) session.getAttribute(ATTR_LANGUAGE);
        String message;

        if("en_Us".equals(language)) {
            message = ResourceManager.getEnMessage(messageKey);
        } else {
            message = ResourceManager.getRuMessage(messageKey);
        }
        return message;
    }
}
