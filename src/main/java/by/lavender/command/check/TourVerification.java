package by.lavender.command.check;

import by.lavender.command.ServerMessage;
import by.lavender.validator.Validator;

import javax.servlet.http.HttpSession;

public class TourVerification {
    public static String checkTourInfo(HttpSession session, String begDate, String endDate,
                                       String cost, String itemNumber) {
        String warn = null;

        if (!Validator.validateDate(begDate, endDate)) {
            warn = ServerMessage.message(session, "message.invalidDate");
        } else if( !Validator.validateCost(cost)) {
            warn = ServerMessage.message(session, "message.invalidPrice");
        } else if( !Validator.validateItemNumber(itemNumber)) {
            warn = ServerMessage.message(session, "message.invalidItem");
        }
        return warn;
    }
}
