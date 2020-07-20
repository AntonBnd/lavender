package by.lavender.command;

import by.lavender.beans.Country;
import by.lavender.service.CountryService;
import by.lavender.service.exception.ServiceException;
import by.lavender.service.impl.CountryServiceImpl;
import by.lavender.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ViewAllCountriesCommand implements ActionCommand {
    private static final String ATTR_WARNING = "warning";
    private static final String ATTR_COUNTRIES = "countries";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        CountryService countryServiceImpl = CountryServiceImpl.getInstance();
        String page = ResourceManager.getProperty("page.countries");
        ArrayList<Country> countries = countryServiceImpl.getAllCountries();

        if(countries.isEmpty()) {
            request.setAttribute(ATTR_WARNING, ServerMessage.message(request.getSession(), "message.noCountries"));
        }
        request.setAttribute(ATTR_COUNTRIES, countries);
        return page;
    }
}
