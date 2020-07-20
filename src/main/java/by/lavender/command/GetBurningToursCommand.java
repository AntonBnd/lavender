package by.lavender.command;

import by.lavender.beans.Tour;
import by.lavender.service.TourService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.TourServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GetBurningToursCommand implements ActionCommand {
    private static final String ATTR_TOURS = "tours";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = ResourceManager.getProperty("page.main");
        TourService tourService = TourServiceImpl.getInstance();
        ArrayList<Tour> tours = tourService.getBurningTours();

        request.setAttribute(ATTR_TOURS, tours);
        return page;
    }
}
