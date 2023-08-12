import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WikipediaLinkCycles {
    public static void main(String[] args) {
        String initialLink = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        int maxTotalLinks = 30;
        int maxCycles = 3;

        HashSet<String> uniqueLinks = new HashSet<>();
        scrapeAndStoreLinksLimitedCycles(initialLink, maxTotalLinks, maxCycles, uniqueLinks);

        // Display the scraped unique links
        System.out.println("Scraped unique links:");
        for (String link : uniqueLinks) {
            System.out.println(link);
        }
    }

    private static void scrapeAndStoreLinksLimitedCycles(String baseUrl, int maxTotalLinks, int cyclesLeft, HashSet<String> uniqueLinks) {
        if (uniqueLinks.size() >= maxTotalLinks || cyclesLeft <= 0) {
            return; // Terminate recursion when limits are reached
        }

        try {
            Document document = Jsoup.connect(baseUrl).get();
            Elements links = document.select("a[href^=\"/wiki/\"]");

            for (Element link : links) {
                String linkHref = link.attr("href");
                if (linkHref.matches("/wiki/.+") && !uniqueLinks.contains(linkHref)) {
                    uniqueLinks.add(linkHref);
                    scrapeAndStoreLinksLimitedCycles("https://en.wikipedia.org" + linkHref, maxTotalLinks, cyclesLeft - 1, uniqueLinks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
