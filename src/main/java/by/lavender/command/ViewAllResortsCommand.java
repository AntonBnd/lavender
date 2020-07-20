package by.lavender.command;

import by.lavender.beans.Resort;
import by.lavender.service.ResortService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.ResortServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ViewAllResortsCommand implements ActionCommand{
    private static final String ATTR_WARNING = "warning";
    private static final String ATTR_RESORTS = "resorts";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        ResortService resortServiceImpl = ResortServiceImpl.getInstance();
        String page = ResourceManager.getProperty("page.resorts");
        ArrayList<Resort> resorts = resortServiceImpl.getAllResorts();

        if (resorts.isEmpty()) {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.noResorts"));
        }
        request.setAttribute(ATTR_RESORTS, resorts);
        return page;
    }
}
