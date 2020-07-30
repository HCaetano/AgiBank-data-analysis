package Analysis;

import Entity.*;

import java.util.ArrayList;

public class Analyst {

    public Analyst() {

    }

    public void processWholeFile(ArrayList<String> fileContents, ArrayList<Salesperson> salespeople,
                                 ArrayList<Customer> customers, Report report) {
        for (String line : fileContents) {
            switch (line.substring(0, 3)) {
                case "001":
                    Salesperson salesperson = new Salesperson();

                    salesperson.setCpf(line.split("ç")[1]);
                    salesperson.setName(line.split("ç")[2]);
                    salesperson.setSalary(Double.parseDouble(line.split("ç")[3]));
                    salespeople.add(salesperson);
                    break;
                case "002":
                    Customer customer = new Customer();

                    customer.setCnpj(line.split("ç")[1]);
                    customer.setName(line.split("ç")[2]);
                    customer.setField(line.split("ç")[3]);
                    customers.add(customer);
                    break;
                case "003":
                    ArrayList<Item> items = this.processSaleItems(line);
                    Sale sale = new Sale();

                    sale.setId(line.split("ç")[1]);
                    sale.setValue(this.processSaleValue(items));
                    sale.setItems(items);
                    report.checkBiggestSale(sale);
                    report.checkSmallestSale(sale, salespeople, line);
                    break;
            }
        }
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

    public String increaseReportNumber(String reportNumber) {
        int updatedReportNumber = Integer.parseInt(reportNumber);
        updatedReportNumber++;

        return Integer.toString(updatedReportNumber);
    }
}
