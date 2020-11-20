import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.util.Currency;
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
public class FakturaItemOperations {

    private final FakturaItemDao fakturaItemDao;
    private final ArticleDao articleDao;
    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat();

    static {
        CURRENCY_FORMAT.setMaximumFractionDigits(2);
        CURRENCY_FORMAT.setMinimumFractionDigits(2);
        CURRENCY_FORMAT.setCurrency(Currency.getInstance("PLN"));
    }

    private static synchronized String formatCurrency(Float number) {
        return CURRENCY_FORMAT.format(number);
    }
    
    public FakturaItemOperations(FakturaItemDao fakturaItemDao, ArticleDao articleDao) {
        this.fakturaItemDao = fakturaItemDao;
        this.articleDao = articleDao;
    }

    public void add(String fakturaId, BufferedReader reader) {
        try {
            System.out.println("Add article to faktura\nEnter article id:");
            String articleId = reader.readLine();
            if (articleDao.checkIfIdExist(articleId)) {
                System.out.println("Enter quantity:");
                float quantity = Float.parseFloat(reader.readLine());
                String fakturaItemId = fakturaItemDao.getNextId();
                fakturaItemDao.add(new FakturaItem(
                        fakturaId,
                        fakturaItemId,
                        articleId,
                        articleDao.getById(articleId).getArticleName(),
                        quantity,
                        articleDao.getById(articleId).getNettoPrice(),
                        articleDao.getById(articleId).getTaxRate()
                ));
                System.out.println("Addition successful.");
                System.out.println("Item id = '" + fakturaItemId + "'");
            } else {
                System.out.println("Id not exist in database.");
                System.out.println("Addition unsuccessful.");
            }
        } catch (Exception e) {
            System.out.println("Unsupported datatype - " + e.getMessage());
            System.out.println("Addition unsuccessful.");
        }
    }

    public void delete(String fakturaItemId) {
        fakturaItemDao.delete(fakturaItemId);
    }

    public static void print(FakturaItem fakturaItem) {
        System.out.println(
                fakturaItem.getFakturaItemId() + "\t"
                + fakturaItem.getArticleName() + "\t"
                + fakturaItem.getQuantity() + "\t"
                + formatCurrency(fakturaItem.getNettoPrice()) + "\t"
                + formatCurrency(fakturaItem.getNettoValue()) + "\t"
                + fakturaItem.getTaxRate() + " %\t"
                + formatCurrency(fakturaItem.getTaxValue()) + "\t"
                + formatCurrency(fakturaItem.getBruttoValue()));
    }

    public static void printItemsForFaktura(Faktura faktura) {
        System.out.println(
                "Nr \t Article Name \t Quantity \t Netto Price \t"
                + "Netto Value \t Tax Rate \t Tax Value \t Brutto Value"
        );
        for (FakturaItem fakturaItem : faktura.getItems()) {
            print(fakturaItem);
        }
    }

}
