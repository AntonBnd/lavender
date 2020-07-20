package by.lavender.command;

import by.lavender.beans.OrderList;
import by.lavender.beans.User;
import by.lavender.service.OrderListService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.OrderListServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class GetUserOrdersCommand implements ActionCommand {
    private static final String ATTR_WARNING = "warning";
    private static final String ATTR_ORDERS = "orders";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        OrderListService orderListService = OrderListServiceImpl.getInstance();
        String page = ResourceManager.getProperty("page.user");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getUserId();
        ArrayList<OrderList> orders = orderListService.getUserOrders(userId);

        if(orders.isEmpty()) {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.noOrders"));
        }
        request.setAttribute(ATTR_ORDERS, orders);
        return page;
    }
}
