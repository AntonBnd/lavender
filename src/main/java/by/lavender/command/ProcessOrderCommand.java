package by.lavender.command;

import by.lavender.service.OrderListService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.OrderListServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ProcessOrderCommand implements ActionCommand {
    private static final String PARAM_ORDER_ID = "orderId";
    private static final String PARAM_STATUS_ID = "status";
    private static final String HEADER_ATTR = "referer";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        long orderId = Long.parseLong(request.getParameter(PARAM_ORDER_ID));
        long statusId = Long.parseLong(request.getParameter(PARAM_STATUS_ID));
        OrderListService orderListService = OrderListServiceImpl.getInstance();
        orderListService.processOrder(statusId, orderId);

        return request.getHeader(HEADER_ATTR);
    }
}
