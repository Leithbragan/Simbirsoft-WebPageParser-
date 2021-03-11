package simbirsoft.pearser.task.services;

public class ParsingService {

    private String controversialPart;

    public boolean checkNumbers(String token) {
        for (int i = 0; i < 10; i++) {
            if (token.contains(Integer.toString(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSymbols(String token) {
        char[] symbols = {'<', '>', '/', '*', '@', '|', '=', '+', '_', '}', '{', '&', '#'};
        for (char symbol : symbols) {
            if (token.lastIndexOf(symbol) != -1) {
                return false;
            }
        }
        return true;
    }

    public String removeMarkupInformation(String partOfHTML) {
        return partOfHTML.replaceAll("<!-{0,}.*-{0,}>", "")
                .replaceAll("<\\s*style><\\s*\\/\\s*style\\s*>", "")
                .replaceAll("<meta(.|\n)*?>", "")
                .replaceAll("<link(.*?)>", "")
                .replaceAll("</html>(.|\n)*", "")
                .replaceAll("<img(.*?)>", "")
                .replaceAll("<svg(.*?)>(.|\n)*?<\\/svg>", "")
                .replaceAll("<title>(.|\n)*?<\\/title>", "")
                .replaceAll("«", "")
                .replaceAll("»", "");
    }

    public boolean isCorrectString(String token) {
        return token.length() > 1 && !token.startsWith("<") && !token.startsWith(">")
                && !token.startsWith("-") && !token.startsWith("—")
                && !token.startsWith("–") && checkSymbols(token) && checkNumbers(token);
    }

    public String formPartOfHTML(String part) {

        String concatenatedString = "";
        StringBuffer trimmedString;

        if (!part.endsWith(" ")) {
            int lastSpaceIndex = part.lastIndexOf(" ");

            trimmedString = new StringBuffer(part);
            trimmedString.replace(lastSpaceIndex + 1, part.length(), "");
            concatenatedString = controversialPart + " " + trimmedString;
            concatenatedString = removeMarkupInformation(concatenatedString);
            controversialPart = part.substring(lastSpaceIndex);
        }
        return concatenatedString;
    }
}
