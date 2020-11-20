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
public class Article {
    private final String articleId;
    private final String articleName;
    private final float nettoPrice;
    private final float taxRate;

    public Article(String articleId, String articleName, float nettoPrice, float taxRate) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.nettoPrice = nettoPrice;
        this.taxRate = taxRate;
    }

    public String getArticleName() {
        return articleName;
    }

    public float getNettoPrice() {
        return nettoPrice;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public String getArticleId() {
        return articleId;
    }
}
