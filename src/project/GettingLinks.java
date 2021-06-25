package project;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GettingLinks {

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        GettingLinks test = new GettingLinks();
        System.out.println("Search > ");
        String query = e.nextLine();
        ArrayList<Product> myProducts = test.searchAmazon(5, query);
        System.out.println("Arraylist contents: ");
        int number = 0;
        for(Product p : myProducts) {
            number += 1;
            System.out.println("\nProduct #" + number + ":");
            System.out.println("Name: " + p.getName());
            System.out.println("Money required for purchasing: " + p.getPrice());
            System.out.println("Customer approval level: " + p.getRating());
            System.out.println("Link to the product so you can buy it: " + p.getLink());
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
     */
    public ArrayList<Product> searchAmazon(int numOfLinks, String query) {
        ArrayList<Product> theLinks = new ArrayList<>();
        if(!query.isEmpty()) {
            try {
                String prefix = "https://www.amazon.ca";
                String URL = prefix + "/s?k=" + query + "&ref=nb_sb_noss";
                Document document = Jsoup.connect(URL)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0")
                        .maxBodySize(0)
                        .timeout(0)
                        .data("is_check", "1")
                        .method(Connection.Method.POST)
                        .get();

                    Elements productContainer = document.getElementsByClass("sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 sg-col sg-col-4-of-20");                    Elements links = productContainer.first().getElementsByClass("a-link-normal a-text-normal");
                        for (int i = 0; i < numOfLinks; i++) {
                            String name = "<Unknown name>";
                            String link = "Nope :(";
                            String price = "Unknown";
                            String rating = "N/A";
                            Elements names = productContainer.get(i).getElementsByClass("a-size-base-plus a-color-base a-text-normal");
                            if(!names.isEmpty()) {
                                name = names.get(0).text();
                            }
                            Elements leLinks = productContainer.get(i).getElementsByClass("a-link-normal a-text-normal");
                            if(!leLinks.isEmpty()) {
                                link = prefix + leLinks.get(0).attributes().get("href");
                            }
                            Elements prices = productContainer.get(i).getElementsByClass("a-offscreen");
                            if(!prices.isEmpty()) {
                                price = prices.get(0).text();
                            }
                            Elements ratings = productContainer.get(i).getElementsByClass("a-icon-alt");
                            if(!ratings.isEmpty()) {
                                rating = ratings.get(0).getElementsByClass("a-icon-alt").text();
                            }
                            Product product = new Product(name, link, price, rating);
                            theLinks.add(product);
                        }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nothing");
        }
        return theLinks;
    }
}