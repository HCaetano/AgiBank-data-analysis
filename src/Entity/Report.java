package Entity;

public class Report {

    private Integer customerQuantity;
    private Integer salesPeopleQuantity;
    private String biggestSaleId;
    private String lowestRankingSalespersonName;
    private String reportNumber;

    public Report(Integer customerQuantity, Integer salesPeopleQuantity, String biggestSaleId,
                  String lowestRankingSalespersonName, String reportNumber) {
        this.customerQuantity = customerQuantity;
        this.salesPeopleQuantity = salesPeopleQuantity;
        this.biggestSaleId = biggestSaleId;
        this.lowestRankingSalespersonName = lowestRankingSalespersonName;
        this.reportNumber = reportNumber;
    }

    @Override
    public String toString() {
        return "*** Report " + reportNumber + " ***" +
                "\nNumber of customers: " + customerQuantity +
                "\nNumber of salespeople: " + salesPeopleQuantity +
                "\nBiggest sale ID: " + biggestSaleId +
                "\nSalesperson with lowest ranking sale: " + lowestRankingSalespersonName +
                "\n*** End report ***\n";
    }
}
