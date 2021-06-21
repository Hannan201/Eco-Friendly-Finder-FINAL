package project;

import java.util.ArrayList;

public class Product {
    private String name;
    private String link;
    private String price;
    private String rating;
    private ArrayList<String> reviews;

    public Product(String name, String link, String price,
                   String rating, ArrayList<String> reviews) {
        this.name = name;
        this.link = link;
        this.price = price;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
