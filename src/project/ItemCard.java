package project;

import javafx.scene.layout.HBox;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class ItemCard implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    private ArrayList<HBox> card;

    public ItemCard (ArrayList<HBox> hBox) {
        this.card = hBox;
    }

    public ArrayList<HBox> getCard() {
        return this.card;
    }
}
