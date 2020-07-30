package Entity;

import java.util.ArrayList;

public class Report {

    private Integer customerQuantity;
    private Integer salesPeopleQuantity;
    private String biggestSaleId;
    private Salesperson lowestRankingSalesperson;
    private String reportNumber;
    private Double biggestSale = 0.0;
    private Double smallestSale = Double.MAX_VALUE;

    public Report() {

    }

    public void setCustomerQuantity(Integer customerQuantity) {
        this.customerQuantity = customerQuantity;
    }

    public void setSalesPeopleQuantity(Integer salesPeopleQuantity) {
        this.salesPeopleQuantity = salesPeopleQuantity;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Double getBiggestSale() {
        return biggestSale;
    }

    public Double getSmallestSale() {
        return smallestSale;
    }

    public void checkBiggestSale(Sale sale) {
        if (sale.getValue() > biggestSale) {
            biggestSale = sale.getValue();
            biggestSaleId = sale.getId();
        }
    }

    public void checkSmallestSale(Sale sale, ArrayList<Salesperson> salespeople, String line) {
        if (sale.getValue() < smallestSale) {
            smallestSale = sale.getValue();
            lowestRankingSalesperson = salespeople.stream()
                    .filter(person -> line.split("ç")[3].equals(person.getName()))
                    .findAny()
                    .orElse(null);
        }
    }

    @Override
    public String toString() {
        return "*** Report " + reportNumber + " ***" +
                "\nNumber of customers: " + customerQuantity +
                "\nNumber of salespeople: " + salesPeopleQuantity +
                "\nBiggest sale ID: " + biggestSaleId +
                "\nSalesperson with lowest ranking sale: " + lowestRankingSalesperson.getName() +
                "\n*** End report ***\n";
    }
}
