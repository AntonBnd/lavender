package by.lavender.command;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        CommandEnum currentEnum = CommandEnum.valueOf(modifyString(action));
        ActionCommand command = currentEnum.getCurrentCommand();
        return command;
    }

    private String modifyString (String st) {
        char[] allLetters = st.toCharArray();
        ArrayList<String> upperCase = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for (char c : allLetters) {
            if(Character.isUpperCase(c)) {
                upperCase.add(String.valueOf(c));
            }
        }

        if(!upperCase.isEmpty()) {
            String[] strings = st.split("[A-Z]");
            for (int i = 0; i < strings.length; i++) {
                if (i <= strings.length - 2) {
                    result.append(strings[i].toUpperCase()+"_"+upperCase.get(i));
                } else {
                    result.append(strings[i].toUpperCase());
                }
            }
        } else {
            return st.toUpperCase();
        }
        return result.toString();
    }
}
