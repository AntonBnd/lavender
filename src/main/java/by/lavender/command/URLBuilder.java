package by.lavender.command;

public class URLBuilder {

    public static String buildFullURL(StringBuffer requestURL, String command,
                                      String parameterName, String parameterValue) {
        return requestURL.append('?').append("command=" + command)
                .append('&').append(parameterName + "=" + parameterValue).toString();
    }

    public static String buildFullURL(StringBuffer requestURL, String command) {
        return requestURL.append('?').append("command=" + command).toString();
    }
}
