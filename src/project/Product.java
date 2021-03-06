package project;

import java.util.ArrayList;

public class Product {
    private String name;
    private String link;
    private String price;
    private String rating;

    public Product(String name, String link, String price,
                   String rating) {
        this.name = name;
        this.link = link;
        this.price = price;
        this.rating = rating;
        formatRating(rating);
    }

    public Product () {}

    private void formatRating(String rating) {
        if (rating.contains("5")) {
            this.rating = rating.substring(0, rating.indexOf(" ")) + "/5.0";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
