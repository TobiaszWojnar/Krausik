import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * The <code>InvoiceApplication</code> is simple application issuing invoices (Polish: <b>Faktura</b>).
 * It is exercise for object oriented programing course in Java
 * at Wroclaw University of Science and Technology.
 * The objective of this list is to implement
 * simple application issuing invoices with GRASP methodology,
 * getting familiar with PMD and Checkstyle plugins
 * and generate UML class diagram.
 *
 * <p><code>Article</code>
 *
 * <p/>

 * @version     19 November 2020
 * @author      Tobiasz Wojnar
 */
import static java.time.LocalDate.now;

public class Faktura {
    /**
     * based on https://www.fakturowo.pl/wystaw
     */
    private final String fakturaNr;
    private final LocalDate dateOfIssue;
    private final String sellerId;
    private final String buyerId;

    private List<FakturaItem> fakturaItems;

    enum Status {
        paid, unpaid
    }

    private Status status;

    public Faktura(String fakturaNr, String sellerId, String buyerId) {
        this.fakturaNr = fakturaNr;
        dateOfIssue = now();
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        fakturaItems = new ArrayList<>();
        status = Faktura.Status.unpaid;
    }

    public String getFakturaId() {
        return fakturaNr;
    }

    public String getId() {
        return getFakturaId();
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public List<FakturaItem> getItems() {
        return fakturaItems;
    }

    public void setFakturaItems(List<FakturaItem> fakturaItems) {
        this.fakturaItems = fakturaItems;
    }

    public Status getStatus() {
        return status;
    }

    public String setStatus(String newStatus) { //TODO implement
        if ("yes".equals(newStatus) || "paid".equals(newStatus)) {
            status = Status.paid;
        } else if ("no".equals(newStatus) || "unpaid".equals(newStatus)) {
            status = Status.unpaid;
        }
        return status.toString();
    }

}
