import java.util.regex.*;

public class Wikipedia {


		 public static boolean isValidWikiLink(String link) {
		        String wikiLinkRegex = "^https?:\\/\\/([a-z]+\\.)?wikipedia\\.org\\/wiki\\/[^/]+$";
		        return Pattern.matches(wikiLinkRegex, link);
		    }

		    public static void main(String[] args) {
		        String inputLink = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		        
		        if (isValidWikiLink(inputLink)) {
		            System.out.println("Valid Wikipedia link.");
		        } else {
		            System.out.println("Invalid Wikipedia link.");
		        }
		    }
}
