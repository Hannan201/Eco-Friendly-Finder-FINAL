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
        ArrayList<Product> myProducts = new ArrayList<>();
        test.searchAmazon(1, myProducts, e);
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
     * @param theLinks : The Arraylist which will contain all
     *                the links that were searched
     */
    public void searchAmazon(int numOfLinks, ArrayList<Product> theLinks, Scanner s) {
        System.out.println("What would you like to search?");
        String query;// = s.nextLine();
        query = "Forza Horizon";
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
                    Elements names = d.getElementsByClass("a-size-base-plus a-color-base a-text-normal");
                    Elements prices = d.getElementsByClass("a-offscreen");
                    //Containing "a-icon a-icon-star-small"
                    Elements ratings = d.getElementsByClass("");
                    if (!links.isEmpty()) {
                        for (int i = 0; i < numOfLinks; i++) {
                            Product product = new Product();
                            String name = names.get(i).text();
                            String link = prefix + links.get(i).attributes().get("href");
                            String price = prices.get(i).text();
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