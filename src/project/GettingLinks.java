package project;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Scanner;

public class GettingLinks {

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        GettingLinks test = new GettingLinks();
        ArrayList<String> myLinks = new ArrayList<>();
        test.searchAmazon(5, myLinks, e);
        System.out.println("Arraylist contents: ");
        for(String s : myLinks) {
            System.out.println(s);
        }
    }

    /**
     * This method takes in a integer to specify the amount
     * of links to be searched on Amazon, which in this
     * case is the amount of products. It stores those
     * links in an ArrayList which contains strings
     * then returns it to be used for other methods.
     *
     * @param numOfLinks : The integer value specifying the
     *                  amount of links/products to be searched for
     * @param theLinks : The Arraylist which will contain all
     *                the links that were searched
     */
    public void searchAmazon(int numOfLinks, ArrayList<String> theLinks, Scanner s) {
        System.out.println("What would you like to search?");
        String query = s.nextLine();
        if(!query.isEmpty()) {
            try {
                String URL = "https://www.amazon.ca/s?k=" + query + "&ref=nb_sb_noss";
                String prefix = "http://www.amazon.ca";
                Document d = Jsoup.connect(URL)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                        .maxBodySize(0)
                        .timeout(0)
                        .data("is_check", "1")
                        .method(Connection.Method.POST)
                        .get();
                Elements es = d.getElementsByClass("a-size-base-plus a-color-base a-text-normal");
                Elements links = d.getElementsByClass("a-link-normal a-text-normal");
                if(!links.isEmpty()) {
                    for (int i = 0; i < numOfLinks; i++) {

                        Element link = links.get(i);
                        Element e = es.get(i);

                        String name = e.text();
                        String url = link.attributes().get("href");

                        System.out.println((i+1) + ")");
                        System.out.println("Name: [" + name + "]");
                        System.out.println("Link: " + "[" + prefix + url + "]\n");
                        theLinks.add(prefix + url);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Nothing");
        }
    }
}
