package Analysis;

import Entity.Customer;
import Entity.Item;
import Entity.Report;
import Entity.Salesperson;
import IO.Reader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalystTest {

    @Test
    public void processWholeFile() {
        ArrayList<String> fileContents = new ArrayList<>();
        Analyst analyst = new Analyst();
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        Reader reader = new Reader();
        Report report = new Report();
        String inputFilePath = System.getProperty("user.home") + "/data/in/file1.dat";

        fileContents = reader.getContentFromFile(inputFilePath, fileContents);
        analyst.processWholeFile(fileContents, salespeople, customers, report);

        double biggestSale = report.getBiggestSale();
        double smallestSale = report.getSmallestSale();

        assertEquals(10000.0, biggestSale, 1e-9);
        assertEquals(393.5, smallestSale, 1e-9);
        assertEquals("Paulo", report.getLowestRankingSalesperson().getName());
        assertEquals(5, salespeople.size());
        assertEquals(2, customers.size());
    }

    @Test
    public void assertThatProcessSaleItemsMethodBehavesCorrectlyWithMultipleItems() {
        Analyst analyst = new Analyst();
        String saleLine = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
        ArrayList<Item> items;
        Item expectedFirstItem = new Item(1, 34, 10.0);
        Item expectedSecondItem = new Item(2, 33, 1.50);
        Item expectedThirdItem = new Item(3, 40, 0.10);

        items = analyst.processSaleItems(saleLine);

        assertEquals(expectedFirstItem.getId(), items.get(0).getId());
        assertEquals(expectedFirstItem.getQuantity(), items.get(0).getQuantity());
        assertEquals(expectedFirstItem.getPrice(), items.get(0).getPrice());
        assertEquals(expectedSecondItem.getId(), items.get(1).getId());
        assertEquals(expectedSecondItem.getQuantity(), items.get(1).getQuantity());
        assertEquals(expectedSecondItem.getPrice(), items.get(1).getPrice());
        assertEquals(expectedThirdItem.getId(), items.get(2).getId());
        assertEquals(expectedThirdItem.getQuantity(), items.get(2).getQuantity());
        assertEquals(expectedThirdItem.getPrice(), items.get(2).getPrice());
    }

    @Test
    public void assertThatLineOfMultipleItemsIsProcessedCorrectly() {
        Analyst analyst = new Analyst();
        String processedLine = "1-34-10,2-33-1.50,3-40-0.10";
        ArrayList<Item> items;
        Item expectedFirstItem = new Item(1, 34, 10.0);
        Item expectedSecondItem = new Item(2, 33, 1.50);
        Item expectedThirdItem = new Item(3, 40, 0.10);

        items = analyst.processMultipleItems(processedLine);

        assertEquals(expectedFirstItem.getId(), items.get(0).getId());
        assertEquals(expectedFirstItem.getQuantity(), items.get(0).getQuantity());
        assertEquals(expectedFirstItem.getPrice(), items.get(0).getPrice());
        assertEquals(expectedSecondItem.getId(), items.get(1).getId());
        assertEquals(expectedSecondItem.getQuantity(), items.get(1).getQuantity());
        assertEquals(expectedSecondItem.getPrice(), items.get(1).getPrice());
        assertEquals(expectedThirdItem.getId(), items.get(2).getId());
        assertEquals(expectedThirdItem.getQuantity(), items.get(2).getQuantity());
        assertEquals(expectedThirdItem.getPrice(), items.get(2).getPrice());
    }

    @Test
    public void assertThatLineOfSingleItemIsProcessedCorrectly() {
        Analyst analyst = new Analyst();
        String processedLine = "1-20-500";
        ArrayList<Item> items;
        Item expectedItem = new Item(1, 20, 500.0);

        items = analyst.processSingleItem(processedLine);

        assertEquals(expectedItem.getId(), items.get(0).getId());
        assertEquals(expectedItem.getQuantity(), items.get(0).getQuantity());
        assertEquals(expectedItem.getPrice(), items.get(0).getPrice());
    }

    @Test
    public void assertThatProcessSaleValueIsCorrectlyCalculated() {
        Analyst analyst = new Analyst();
        double saleValueActual;
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, 10, 100.00));
        items.add(new Item(2, 30, 2.50));
        items.add(new Item(3, 40, 00.10));

        saleValueActual = analyst.processSaleValue(items);

        assertEquals(1079.0, saleValueActual, 1e-9);
    }

    @Test
    public void assertThatReportNumberIsIncreased() {
        Analyst analyst = new Analyst();
        String reportNumber = "1";

        reportNumber = analyst.increaseReportNumber(reportNumber);

        assertEquals("2", reportNumber);
    }
}