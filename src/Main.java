import Entity.*;
import IO.Reader;
import IO.Writer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileContents = new ArrayList<>();
        Report report;
        String reportNumberPath = System.getProperty("user.dir") + "/src/resources/reportNumber.dat";
        String inputFilePath = System.getProperty("user.home") + "/data/in/file1.dat";
        String reportNumber;
        double smallestSale = Double.MAX_VALUE;
        double biggestSale = 0.0;
        String biggestSaleId = "";
        Salesperson lowestRankingSalesperson = new Salesperson();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        Reader reader = new Reader();
        Writer writer = new Writer();

        fileContents = reader.getContentFromFile(inputFilePath, fileContents);
        reportNumber = reader.getContentFromFile(reportNumberPath);
        Integer updatedReportNumber = Integer.parseInt(reportNumber);
        updatedReportNumber++;
        reportNumber = updatedReportNumber.toString();

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
                    int commaCounter = line.replaceAll("[^,]", "").length();
                    double saleValue = 0.0;
                    Sale sale = new Sale();
                    ArrayList<Item> items = new ArrayList<>();

                    String processedLine = line.split("\\[")[1];
                    processedLine = processedLine.split("\\]")[0];
                    sale.setId(line.split("ç")[1]);

                    if (commaCounter > 0) {
                        String[] itemsContent = processedLine.split(",");

                        for (String itemContent : itemsContent) {
                            String itemsInfo[] = itemContent.split("-");
                            Item item = new Item();

                            item.setId(Integer.parseInt(itemsInfo[0]));
                            item.setQuantity(Integer.parseInt(itemsInfo[1]));
                            item.setPrice(Double.parseDouble(itemsInfo[2]));
                            items.add(item);

                            saleValue += item.getQuantity() * item.getPrice();
                        }

                        sale.setValue(saleValue);
                        sale.setItems(items);

                        if (sale.getValue() > biggestSale) {
                            biggestSale = sale.getValue();
                            biggestSaleId = sale.getId();
                        }

                        if (sale.getValue() < smallestSale) {
                            smallestSale = sale.getValue();
                            lowestRankingSalesperson = salespeople.stream()
                                    .filter(person -> line.split("ç")[3].equals(person.getName()))
                                    .findAny()
                                    .orElse(null);
                        }
                    } else {
                        String itemsInfo[] = processedLine.split("-");
                        Item item = new Item();

                        item.setId(Integer.parseInt(itemsInfo[0]));
                        item.setQuantity(Integer.parseInt(itemsInfo[1]));
                        item.setPrice(Double.parseDouble(itemsInfo[2]));
                        items.add(item);

                        sale.setValue(item.getQuantity() * item.getPrice());
                        sale.setItems(items);

                        if (sale.getValue() > biggestSale) {
                            biggestSale = sale.getValue();
                            biggestSaleId = sale.getId();
                        }

                        if (sale.getValue() < smallestSale) {
                            smallestSale = sale.getValue();
                            lowestRankingSalesperson = salespeople.stream()
                                    .filter(person -> line.split("ç")[3].equals(person.getName()))
                                    .findAny()
                                    .orElse(null);
                        }
                    }
                    break;
            }
        }

        report = new Report(customers.size(), salespeople.size(), biggestSaleId, lowestRankingSalesperson.getName(), reportNumber);

        writer.writeReportNumber(reportNumber, reportNumberPath);
        writer.writeReport(reportNumber, report);
    }
}

