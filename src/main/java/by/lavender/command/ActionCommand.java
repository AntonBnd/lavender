package by.lavender.command;

import by.lavender.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws ServiceException;
}
