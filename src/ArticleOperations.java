import java.io.BufferedReader;

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
public class ArticleOperations {

    private final ArticleDao articleDao;

    public ArticleOperations(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void add(BufferedReader reader) {
        try {
            System.out.println("Add article to database.");
            System.out.println("Enter article name:");
            String articleName = reader.readLine();
            System.out.println("Enter a id:");
            String articleId = reader.readLine();
            if (articleDao.checkIfIdExist(articleId)) {
                System.out.println("Id exist in database. Addition unsuccessful");
            } else {
                System.out.println("Enter netto price:");
                float nettoPrice = Float.parseFloat(reader.readLine());
                System.out.println("Enter tax rate (percentage):");
                float taxRate = Float.parseFloat(reader.readLine());

                articleDao.add(new Article(articleId, articleName, nettoPrice, taxRate));
                System.out.println("Addition successful");
            }
        } catch (Exception e) {
            System.out.println("Unsupported datatype - " + e.getMessage());
            System.out.println("Addition unsuccessful");
        }

    }

    public void delete(String articleId) {
        articleDao.delete(articleId);
    }

    public void print(Article article) {
        System.out.println("Name\t" + article.getArticleName());
        System.out.println("Id\t" + article.getArticleId());
        System.out.println("Netto prize\t" + article.getNettoPrice());
        System.out.println("Tax rate\t" + article.getTaxRate());
        System.out.println("------");
    }

    public void print(String articleId) {
        Article article = articleDao.getById(articleId);
        print(article);
    }

    public void printAll() {
        for (Article article : articleDao.getAll()) {
            print(article);
        }
    }

}
