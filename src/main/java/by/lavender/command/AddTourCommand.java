package by.lavender.command;

import by.lavender.command.check.TourVerification;
import by.lavender.beans.Resort;
import by.lavender.beans.Tour;
import by.lavender.beans.TourOperator;
import by.lavender.beans.TourType;
import by.lavender.service.ResortService;
import by.lavender.service.TourOperatorService;
import by.lavender.service.TourService;
import by.lavender.service.TourTypeService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.ResortServiceImpl;
import by.lavender.service.impl.TourOperatorServiceImpl;
import by.lavender.service.impl.TourServiceImpl;
import by.lavender.service.impl.TourTypeServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class AddTourCommand implements ActionCommand {
    private static final String PARAM_TOUR_TYPE_ID = "tourTypeId";
    private static final String PARAM_RESORT_ID = "resortId";
    private static final String PARAM_PRICE = "price";
    private static final String PARAM_DISCRIPTION = "discription";
    private static final String PARAM_TOUR_OPERATOR_ID = "tourOperatorId";
    private static final String PARAM_ITEM_NUMBER = "itemNumber";
    private static final String PARAM_START_DATE = "startDate";
    private static final String PARAM_END_DATE = "endDate";
    private static final String PARAM_STATUS = "status";
    private static final String ATTR_MESSAGE_VALUE = "success";
    private static final String ATTR_TOUR_TYPES = "tourTypes";
    private static final String ATTR_RESORTS = "resorts";
    private static final String ATTR_TOUR_OPERATORS = "tourOperators";
    private static final String ATTR_MESSAGE = "message";
    private static final String COMMAND = "getAddData";
    private static final String ATTR_WARNING = "warning";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        TourService tourService = TourServiceImpl.getInstance();
        long tourTypeId = Long.parseLong(request.getParameter(PARAM_TOUR_TYPE_ID));
        long resortId = Long.parseLong(request.getParameter(PARAM_RESORT_ID));
        long tourOperatorId = Long.parseLong(request.getParameter(PARAM_TOUR_OPERATOR_ID));
        int status = Integer.parseInt(request.getParameter(PARAM_STATUS));
        String discription = request.getParameter(PARAM_DISCRIPTION);

        String costStr = request.getParameter(PARAM_PRICE);
        String itemNumberStr = request.getParameter(PARAM_ITEM_NUMBER);
        String begDateStr = request.getParameter(PARAM_START_DATE);
        String endDateStr = request.getParameter(PARAM_END_DATE);
        String warn = TourVerification.checkTourInfo(request.getSession(), begDateStr, endDateStr, costStr, itemNumberStr);
        if(warn != null) {
            request.setAttribute(ATTR_WARNING, warn);
            return getTourInformation(request);
        }
        double cost = Double.parseDouble(costStr);
        int itemNumber = Integer.parseInt(itemNumberStr);
        Date begDate = Date.valueOf(begDateStr);
        Date endDate = Date.valueOf(endDateStr);

        tourService.createTour(new Tour(begDate, endDate, new TourType(tourTypeId), new Resort(resortId),
                cost, discription, new TourOperator(tourOperatorId), itemNumber, status));
        return URLBuilder.buildFullURL(request.getRequestURL(), COMMAND, ATTR_MESSAGE, ATTR_MESSAGE_VALUE);
    }

    private String getTourInformation(HttpServletRequest request) throws ServiceException {
        TourTypeService tourTypeService = TourTypeServiceImpl.getInstance();
        ResortService resortService = ResortServiceImpl.getInstance();
        TourOperatorService tourOperatorService = TourOperatorServiceImpl.getInstance();
        request.setAttribute(ATTR_TOUR_TYPES, tourTypeService.getTourTypes());
        request.setAttribute(ATTR_RESORTS, resortService.getAllResorts());
        request.setAttribute(ATTR_TOUR_OPERATORS, tourOperatorService.getAllTourOperators());
        return ResourceManager.getProperty("page.addTour");
    }
}
