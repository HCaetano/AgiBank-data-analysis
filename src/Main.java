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
        analyst.processWholeFile(fileContents, salespeople, customers, report);

        reportNumber = reader.getContentFromFile(reportNumberPath);
        reportNumber = analyst.increaseReportNumber(reportNumber);

        report.setCustomerQuantity(customers.size());
        report.setSalesPeopleQuantity(salespeople.size());
        report.setReportNumber(reportNumber);

        writer.writeReportNumber(reportNumber, reportNumberPath);
        writer.writeReport(reportNumber, report);
    }
}

