package by.lavender.tag;

import by.lavender.beans.User;
import by.lavender.command.ServerMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HeaderTag extends TagSupport {
    private static Logger logger = LogManager.getLogger(HeaderTag.class);
    private static final String SESSION_ATTR_ROLE = "role";
    private static final String SESSION_ATR_USER = "user";

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        String role = (String) session.getAttribute(SESSION_ATTR_ROLE);
        JspWriter out = pageContext.getOut();
        try {
            if(role == null){
                out.write("<li><a href='/jsp/login.jsp' ><span class='glyphicon glyphicon-log-in'></span>" +
                        ServerMessage.message(session, "label.signIn") + "</a></li>");
                out.write("<li><a href='/jsp/registration.jsp' ><span class='glyphicon glyphicon-user'></span>" +
                        ServerMessage.message(session, "label.signUp") + "</a></li>");
            } else if(!role.isEmpty()) {
                String hello = ServerMessage.message(session, "label.hello");
                User user = (User) session.getAttribute(SESSION_ATR_USER);
                out.write("<li><p class='navbar-text text-primary'>" + hello + user.getLogin() +"</p></li>");
                out.write("<li><a href='/epam?command=logout'>" + ServerMessage.message(session, "label.signOut") + "</a></li>");
            }
        } catch (IOException e) {
            logger.error("Cannot display header tag");
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
