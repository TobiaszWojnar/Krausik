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
public class ArticleMemoryDao implements ArticleDao {
    List<Article> articleList;

    public ArticleMemoryDao() {
        articleList = new ArrayList<>();
    }

    @Override
    public void delete(String articleId) {
        articleList.remove(getById(articleId));
    }

    @Override
    public String add(Article article) {
        articleList.add(article);
        return article.getArticleId();
    }

    @Override
    public Article getById(String articleId) {
        for (Article article : articleList) {
            if (article.getArticleId().equals(articleId)) {
                return article;
            }
        }
        return null;
    }


    @Override
    public List<Article> getAll() {
        return articleList;
    }

    @Override
    public boolean checkIfIdExist(String articleId) {
        boolean exist = false;
        for (Article article : articleList) {
            if (article.getArticleId().equals(articleId)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
