package Entity;

import java.util.ArrayList;

public class Sale {

    private String id;
    private ArrayList<Item> items;
    private Double value;

    public Sale() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
