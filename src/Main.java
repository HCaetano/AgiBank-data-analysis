import Entity.Customer;
import Entity.Item;
import Entity.Sale;
import Entity.Salesperson;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final ArrayList<String> fileContents = new ArrayList<>();
        final ArrayList<String> report = new ArrayList<>();
        String reportNumberPath = System.getProperty("user.dir") + "/src/resources/reportNumber.dat";
        String reportNumber = "1";
        File data = null;
        double biggestSale = 0.0;
        double smallestSale = Double.MAX_VALUE;
        String biggestSaleId = "";
        Salesperson lowestRankingSalesperson = new Salesperson();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        ArrayList<Sale> sales = new ArrayList<>();

        try {
            data = new File(System.getProperty("user.home") + "/data/in/file1.dat");
            Scanner scanner = new Scanner(data);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContents.add(line);
            }

            File reportNumberInfo = new File(reportNumberPath);
            scanner = new Scanner(reportNumberInfo);

            while (scanner.hasNextLine()) {
                reportNumber = scanner.nextLine();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

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

        int updatedReportNumber = Integer.parseInt(reportNumber);
        updatedReportNumber++;
        reportNumber = Integer.toString(updatedReportNumber);
        report.add("*** Report " + reportNumber + " ***");

        for (int reportIndicator = 0; reportIndicator < 4; reportIndicator++) {
            switch (reportIndicator) {
                case 0:
                    report.add("Number of customers: " + customers.size());
                    break;
                case 1:
                    report.add("Number of salespeople: " + salespeople.size());
                    break;
                case 2:
                    report.add("Biggest sale ID: " + biggestSaleId);
                    break;
                case 3:
                    report.add("Salesperson with lowest ranking sale: " + lowestRankingSalesperson.getName());
            }
        }

        report.add("*** End report ***");
        report.add("\n");

        Path reportDestination = Paths.get(
                System.getProperty("user.home") + "/data/out/report_" + reportNumber + ".done.dat"
        );
        Path reportNumberDestination = Paths.get(reportNumberPath);

        try {
            byte[] stringToBytes = reportNumber.getBytes();

            Files.write(reportNumberDestination, stringToBytes);

            if (data.length() == 0) {
                Files.write(reportDestination, report, Charset.defaultCharset());
            } else {
                String reportInOneString = String.join("\n", report);
                Files.write(reportDestination, reportInOneString.getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

