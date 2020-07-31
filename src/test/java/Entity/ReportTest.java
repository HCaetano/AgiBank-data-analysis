package Entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ReportTest {

    @Test
    public void assertThatSaleIsTheBiggestSale() {
        Report report = new Report();
        Sale sale = new Sale();
        double biggestSaleActual;

        sale.setValue(250.00);
        report.checkBiggestSale(sale);
        sale.setValue(350.00);
        report.checkBiggestSale(sale);
        sale.setValue(150.00);
        report.checkBiggestSale(sale);

        biggestSaleActual = report.getBiggestSale();

        assertEquals(350.00, biggestSaleActual, 1e-9);
    }

    @Test
    public void assertThatSaleIsTheSmallestSale() {
        Report report = new Report();
        Sale sale = new Sale();
        double smallestSaleActual;
        List<String> lines = Arrays.asList("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo",
                "003ç09ç[1-20-500]çJoão"
        );
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        salespeople.add(new Salesperson("Pedro", "1234567891234", 50000.00));
        salespeople.add(new Salesperson("America", "1234567891987", 50000.00));
        salespeople.add(new Salesperson("Paulo", "3245678865434", 40000.99));
        salespeople.add(new Salesperson("João", "6541567891234", 30000.00));
        salespeople.add(new Salesperson("Maria", "6556557891234", 20000.00));

        sale.setValue(250.00);
        report.checkSmallestSale(sale, salespeople, lines.get(0));
        sale.setValue(350.00);
        report.checkSmallestSale(sale, salespeople, lines.get(1));
        sale.setValue(150.00);
        report.checkSmallestSale(sale, salespeople, lines.get(2));

        smallestSaleActual = report.getSmallestSale();

        assertEquals(150.00, smallestSaleActual, 1e-9);
    }
}