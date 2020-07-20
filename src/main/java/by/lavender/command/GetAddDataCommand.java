package by.lavender.command;

import by.lavender.service.ResortService;
import by.lavender.service.TourOperatorService;
import by.lavender.service.TourTypeService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.ResortServiceImpl;
import by.lavender.service.impl.TourOperatorServiceImpl;
import by.lavender.service.impl.TourTypeServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class GetAddDataCommand implements ActionCommand {
    private static final String ATTR_TOUR_TYPES = "tourTypes";
    private static final String ATTR_RESORTS = "resorts";
    private static final String ATTR_TOUR_OPERATORS = "tourOperators";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = ResourceManager.getProperty("page.addTour");
        TourTypeService tourTypeService = TourTypeServiceImpl.getInstance();
        ResortService resortService = ResortServiceImpl.getInstance();
        TourOperatorService tourOperatorService = TourOperatorServiceImpl.getInstance();
        request.setAttribute(ATTR_TOUR_TYPES, tourTypeService.getTourTypes());
        request.setAttribute(ATTR_RESORTS, resortService.getAllResorts());
        request.setAttribute(ATTR_TOUR_OPERATORS, tourOperatorService.getAllTourOperators());
        return page;
    }
}
