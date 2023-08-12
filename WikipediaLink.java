import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WikipediaLink{
    public static void main(String[] args) {
        String initialLink = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        int maxUniqueLinks = 10;

        HashSet<String> uniqueLinks = new HashSet<>();
        scrapeLinks(initialLink, maxUniqueLinks, uniqueLinks);

        // Display the scraped unique links
        System.out.println("Scraped unique links:");
        for (String link : uniqueLinks) {
            System.out.println(link);
        }
    }

    private static void scrapeLinks(String baseUrl, int maxUniqueLinks, HashSet<String> uniqueLinks) {
        try {
            Document document = Jsoup.connect(baseUrl).get();
            Elements links = document.select("a[href^=\"/wiki/\"]");

            for (Element link : links) {
                String linkHref = link.attr("href");
                if (linkHref.matches("/wiki/.+") && uniqueLinks.size() < maxUniqueLinks) {
                    uniqueLinks.add(linkHref);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

