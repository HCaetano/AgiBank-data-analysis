import Analysis.Analyst;
import Entity.*;
import IO.Reader;
import IO.Writer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileContents = new ArrayList<>();
        Report report = new Report();
        String reportNumberPath = System.getProperty("user.dir") + "/src/resources/reportNumber.dat";
        String inputFilePath = System.getProperty("user.home") + "/data/in/file1.dat";
        String reportNumber;
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        Reader reader = new Reader();
        Writer writer = new Writer();
        Analyst analyst = new Analyst();

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
                    Boolean multipleItems = line.replaceAll("[^,]", "").length() > 0;
                    double saleValue;
                    Sale sale = new Sale();
                    ArrayList<Item> items;

                    String processedLine = line.split("\\[")[1];
                    processedLine = processedLine.split("\\]")[0];
                    sale.setId(line.split("ç")[1]);

                    if (multipleItems) {
                        items = analyst.processMultipleItems(processedLine);
                    } else {
                        items = analyst.processSingleItem(processedLine);
                    }

                    saleValue = analyst.processSaleValue(items);
                    sale.setValue(saleValue);
                    sale.setItems(items);
                    report.checkBiggestSale(sale);
                    report.checkSmallestSale(sale, salespeople, line);
                    break;
            }
        }

        report.setCustomerQuantity(customers.size());
        report.setSalesPeopleQuantity(salespeople.size());
        report.setReportNumber(reportNumber);

        writer.writeReportNumber(reportNumber, reportNumberPath);
        writer.writeReport(reportNumber, report);
    }
}

