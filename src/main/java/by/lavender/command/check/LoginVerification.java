package by.lavender.command.check;

import by.lavender.command.ServerMessage;
import by.lavender.validator.Validator;

import javax.servlet.http.HttpSession;

public class LoginVerification {
    public static String checkLoginData(HttpSession session, String username, String password) {
        String warn = null;

        if (!Validator.validateUsername(username)) {
            warn = ServerMessage.message(session, "message.username");
        } else if (!Validator.validatePassword(password)) {
            warn = ServerMessage.message(session, "message.password");
        }
        return warn;
    }
}
