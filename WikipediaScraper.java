import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WikipediaScraper {
    public static void main(String[] args) {
        String initialLink = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        int maxTotalLinks = 30;
        int maxDepth = 3;

        HashSet<String> uniqueLinks = new HashSet<>();
        scrapeAndStoreLinks(initialLink, maxTotalLinks, maxDepth, uniqueLinks);

        // Display the scraped unique links
        System.out.println("Scraped unique links:");
        for (String link : uniqueLinks) {
            System.out.println(link);
        }
    }

    private static void scrapeAndStoreLinks(String baseUrl, int maxTotalLinks, int maxDepth, HashSet<String> uniqueLinks) {
        if (uniqueLinks.size() >= maxTotalLinks || maxDepth <= 0) {
            return; // Terminate recursion when limits are reached
        }

        try {
            Document document = Jsoup.connect(baseUrl).get();
            Elements links = document.select("a[href^=\"/wiki/\"]");

            for (Element link : links) {
                String linkHref = link.attr("href");
                if (linkHref.matches("/wiki/.+") && !uniqueLinks.contains(linkHref)) {
                    uniqueLinks.add(linkHref);
                    scrapeAndStoreLinks("https://en.wikipedia.org" + linkHref, maxTotalLinks, maxDepth - 1, uniqueLinks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

