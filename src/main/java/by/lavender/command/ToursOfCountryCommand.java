package by.lavender.command;

import by.lavender.beans.Tour;
import by.lavender.service.TourService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.TourServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToursOfCountryCommand implements ActionCommand {
    private static final String PARAM_ID = "id";
    private static final String ATTR_WARNING = "warning";
    private static final String ATTR_TOURS = "tours";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        TourService service = TourServiceImpl.getInstance();
        String page = ResourceManager.getProperty("page.tours");
        long id = Long.parseLong(request.getParameter(PARAM_ID));
        ArrayList<Tour> tours = service.getToursByCountry(id);

        if(tours.isEmpty()) {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.noTours"));
        }
        request.setAttribute(ATTR_TOURS, tours);
        return page;
    }
}
