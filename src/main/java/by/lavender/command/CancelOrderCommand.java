package by.lavender.command;

import by.lavender.service.OrderListService;
import by.lavender.service.TourService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.OrderListServiceImpl;
import by.lavender.service.impl.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements ActionCommand {
    private static final String PARAM_TOUR_ID = "tourId";
    private static final String PARAM_ORDER_ID = "orderId";
    private static final String PARAM_TOUR_ITEMS = "totalNumber";
    private static final String PARAM_ORDER_ITEMS = "itemNumber";
    private static final String HEADER_ATTR = "referer";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long tourId = Long.parseLong(request.getParameter(PARAM_TOUR_ID));
        long orderId = Long.parseLong(request.getParameter(PARAM_ORDER_ID));
        int allItems = Integer.parseInt(request.getParameter(PARAM_TOUR_ITEMS));
        int orderItems = Integer.parseInt(request.getParameter(PARAM_ORDER_ITEMS));
        OrderListService orderListService = OrderListServiceImpl.getInstance();
        TourService tourService = TourServiceImpl.getInstance();

        orderListService.deleteOrder(orderId);
        tourService.updateSeatsNumber(tourId, allItems, -orderItems);
        return request.getHeader(HEADER_ATTR);
    }
}
