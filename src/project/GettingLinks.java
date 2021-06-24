package project;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GettingLinks {

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        GettingLinks test = new GettingLinks();
        ArrayList<Product> myProducts = new ArrayList<>();
        test.searchAmazon(1, myProducts, e);
        System.out.println("Arraylist contents: ");
        int number = 0;
//        for(Product p : myProducts) {
//            number += 1;
//            System.out.println("\nProduct #" + number + ":");
//            System.out.println("Name: " + p.getName());
//            //System.out.println("Money required for purchasing: " + p.getPrice());
//            System.out.println("Customer approval level: " + p.getRating());
//            //System.out.println("Reviews: " + p.getReviews());
//            System.out.println("Link to the product so you can buy it: " + p.getLink());
//        }
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
    public void searchAmazon(int numOfLinks, ArrayList<Product> theLinks, Scanner s) {
        String link;
        String[] searchLinks = new String[numOfLinks];
        System.out.println("What would you like to search?");
        String query = s.nextLine();
        if(!query.isEmpty()) {
            try {
                String prefix = "https://www.amazon.ca";
                String URL = prefix + "/s?k=" + query + "&ref=nb_sb_noss";
                Document d = Jsoup.connect(URL)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0")
                        .maxBodySize(0)
                        .timeout(0)
                        .data("is_check", "1")
                        .method(Connection.Method.POST)
                        .get();

                Elements links = d.getElementsByClass("a-link-normal a-text-normal");
                if(!links.isEmpty()) {
                    for (int i = 0; i < numOfLinks; i++) {
                        link = prefix + links.get(i).attributes().get("href");
                        searchLinks[i] = link;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
                for(int in = 0; in < numOfLinks; in += 1) {
                    try {
                        Document doc = Jsoup.connect(searchLinks[in])
                                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0")
                                .maxBodySize(0)
                                .timeout(0)
                                .data("is_check", "1")
                                .method(Connection.Method.POST)
                                .get();

                        Product product = new Product();
                        product.setLink(searchLinks[in]);

                        String name = doc.getElementsByTag("meta").attr("title");
                        System.out.println(name);

                        //Elements ratings = doc.getElementsByClass("a-icon-alt");

//                        String name = names.first().text();
//                        String rating = ratings.get(0).text();
//                        if (!rating.contains("stars")) {
//                            rating = "N/A";
//                        }
//
//                        product.setName(name);
//                        product.setRating(rating);
                    theLinks.add(product);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Nothing");
        }
    }
}
