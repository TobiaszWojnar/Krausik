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
public class FakturaItem extends Article {

    private final String fakturaId;
    private final String fakturaItemId;
    private final float quantity;

    public FakturaItem(String fakturaId, String fakturaItemId,
                       String articleId, String articleName,
                       float quantity, float nettoPrice, float taxRate) {
        super(articleId, articleName, nettoPrice, taxRate);
        this.fakturaId = fakturaId;
        this.fakturaItemId = fakturaItemId;
        this.quantity = quantity;
    }

    public String getFakturaId() {
        return fakturaId;
    }

    public String getFakturaItemId() {
        return fakturaItemId;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getNettoValue() {
        return getQuantity() * getNettoPrice();
    }

    public float getTaxValue() {
        return getQuantity() * getTaxRate() / 100;
    }

    public float getBruttoValue() {
        return getTaxRate() * getQuantity() * (100 + getTaxRate()) / 100;
    }

}
