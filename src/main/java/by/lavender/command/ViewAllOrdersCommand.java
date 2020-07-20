package by.lavender.command;

import by.lavender.beans.OrderList;
import by.lavender.service.OrderListService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.OrderListServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ViewAllOrdersCommand implements ActionCommand {
    private static final String ATTR_WARNING = "warning";
    private static final String ATTR_ORDERS = "orders";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = ResourceManager.getProperty("page.allOrders");
        OrderListService orderListService = OrderListServiceImpl.getInstance();
        ArrayList<OrderList> orders = orderListService.getAllOrders();

        if(orders.isEmpty()) {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.noOrders"));
        }
        request.setAttribute(ATTR_ORDERS, orders);
        return page;
    }
}
