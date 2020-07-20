package by.lavender.command.check;

import by.lavender.command.ServerMessage;
import by.lavender.beans.Tour;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.TourServiceImpl;

import javax.servlet.http.HttpSession;

public class TourItemVerification {

    public static String checkItem(HttpSession session, long tourId, int totalNum, int num) throws ServiceException {
        String warn = null;
        int itemsNumber = 0;

        Tour tour = TourServiceImpl.getInstance().getTourById(tourId);
        if (tour != null) {
            itemsNumber = tour.getNumberOfSeats();
        } else {
            warn = ServerMessage.message(session, "message.tour");
        }

        if (num < 1 || num > totalNum || totalNum != itemsNumber) {
            warn = ServerMessage.message(session, "message.items");
        }
        return warn;
    }
}