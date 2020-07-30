package IO;

import Analysis.Analyst;
import Entity.Customer;
import Entity.Report;
import Entity.Salesperson;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WriterTest {

    @Test
    public void assertThatReportNumberWasWrittenToFile() {
        Writer writer = new Writer();
        Reader reader = new Reader();
        Analyst analyst = new Analyst();
        String reportNumberPath = System.getProperty("user.dir") + "/src/test/java/resources/reportNumber.dat";
        String reportNumberActual = reader.getContentFromFile(reportNumberPath);
        String reportNumberRestored;

        reportNumberActual = analyst.increaseReportNumber(reportNumberActual);
        writer.writeReportNumber(reportNumberActual, reportNumberPath);

        //restore report number to initial value, allows multiple runs of test without breaking
        reportNumberRestored = "1";
        writer.writeReportNumber(reportNumberRestored, reportNumberPath);

        assertEquals("2", reportNumberActual);
    }

    @Test
    public void assertThatReportWasWrittenToFile() {
        Writer writer = new Writer();
        Reader reader = new Reader();
        Report reportActual = new Report();
        Report reportExpected = new Report();
        Analyst analyst = new Analyst();
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        String inputFilePath = System.getProperty("user.dir") + "/src/test/java/resources/inputFile.dat";
        String reportNumber = "1";
        ArrayList<String> fileContents = new ArrayList<>();
        ArrayList<String> fileContentsActual = new ArrayList<>();

        fileContents = reader.getContentFromFile(inputFilePath, fileContents);
        analyst.processWholeFile(fileContents, salespeople, customers, reportExpected);
        reportExpected.setCustomerQuantity(customers.size());
        reportExpected.setSalesPeopleQuantity(salespeople.size());
        reportExpected.setReportNumber(reportNumber);
        writer.writeReportNumber(reportNumber, reportExpected.toString());
        salespeople = new ArrayList<>();
        customers = new ArrayList<>();

        fileContentsActual = reader.getContentFromFile(inputFilePath, fileContentsActual);
        analyst.processWholeFile(fileContentsActual, salespeople, customers, reportActual);
        reportActual.setCustomerQuantity(customers.size());
        reportActual.setSalesPeopleQuantity(salespeople.size());
        reportActual.setReportNumber(reportNumber);

        assertEquals(reportExpected.toString(), reportActual.toString());
    }
}