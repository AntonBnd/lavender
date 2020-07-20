package by.lavender.command.check;

import by.lavender.command.ServerMessage;
import by.lavender.validator.Validator;

import javax.servlet.http.HttpSession;

public class RegistrationCheck {
    public static String checkRegistrationData(HttpSession session, String firstName, String lastName,
                                               String telNumber, String username, String password) {
        String warn = null;

        if (!Validator.validateName(firstName)) {
            warn = ServerMessage.message(session, "message.firstName");
        } else if (!Validator.validateName(lastName)) {
            warn = ServerMessage.message(session, "message.lastName");
        } else if (!Validator.validatePhoneNumber(telNumber)) {
            warn = ServerMessage.message(session, "message.telNumber");
        } else if (!Validator.validateUsername(username)) {
            warn = ServerMessage.message(session, "message.username");
        } else if (!Validator.validatePassword(password)) {
            warn = ServerMessage.message(session, "message.password");
        }
        return warn;
    }
}
