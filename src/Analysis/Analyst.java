package Analysis;

import Entity.Item;
import java.util.ArrayList;

public class Analyst {

    public Analyst() {

    }

    public ArrayList<Item> processSaleItems(String saleLine) {
        boolean multipleItems = saleLine.replaceAll("[^,]", "").length() > 0;
        String processedLine = saleLine.split("\\[")[1];
        processedLine = processedLine.split("\\]")[0];
        ArrayList<Item> items;

        if (multipleItems) {
            items = this.processMultipleItems(processedLine);
        } else {
            items = this.processSingleItem(processedLine);
        }

        return items;
    }

    public ArrayList<Item> processMultipleItems(String processedLine) {
        String[] itemsContent = processedLine.split(",");
        ArrayList<Item> items = new ArrayList<>();

        for (String itemContent : itemsContent) {
            String[] itemsInfo = itemContent.split("-");
            Item item = new Item();

            item.setId(Integer.parseInt(itemsInfo[0]));
            item.setQuantity(Integer.parseInt(itemsInfo[1]));
            item.setPrice(Double.parseDouble(itemsInfo[2]));
            items.add(item);
        }

        return items;
    }

    public ArrayList<Item> processSingleItem(String processedLine) {
        String[] itemsInfo = processedLine.split("-");
        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item();

        item.setId(Integer.parseInt(itemsInfo[0]));
        item.setQuantity(Integer.parseInt(itemsInfo[1]));
        item.setPrice(Double.parseDouble(itemsInfo[2]));
        items.add(item);

        return items;
    }

    public double processSaleValue(ArrayList<Item> items) {
        double saleValue = 0.0;

        for (Item item : items) {
            saleValue += item.getQuantity() * item.getPrice();
        }

        return saleValue;
    }
}
