package by.lavender.tag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TourStatusTag extends TagSupport {
    private static Logger logger = LogManager.getLogger(HeaderTag.class);
    private String tourStatus;

    public void setTourStatus(String tourStatus) {
        this.tourStatus = tourStatus;
    }

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            if("processed".equals(tourStatus)) {
                out.write("<span class='label label-info'>" + tourStatus + "</span>");
            } else if("approved".equals(tourStatus)) {
                out.write("<span class='label label-warning'>" + tourStatus + "</span>");
            } else if("canceled".equals(tourStatus)) {
                out.write("<span class='label label-danger'>" + tourStatus + "</span>");
            } else if("paid".equals(tourStatus)) {
                out.write("<span class='label label-success'>" + tourStatus + "</span>");
            }
        } catch (IOException e) {
            logger.error("Cannot display tour status tag", e);
        }
        return SKIP_BODY;
    }
}
