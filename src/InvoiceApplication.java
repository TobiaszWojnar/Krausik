/**
 * The <code>InvoiceApplication</code> is simple application issuing invoices (Polish: <b>Faktura</b>).
 * It is exercise for object oriented programing course in Java
 * at Wroclaw University of Science and Technology.
 * The objective of this list is to implement
 * simple application issuing invoices with GRASP methodology,
 * getting familiar with PMD and Checkstyle plugins
 * and generate UML class diagram.
 *
 * <p><code>InvoiceApplication</code> mocking object databases, GUI and operational classes.
 *     The mocking GUI is a {@link CommandLineMenu}.
 *     In this applications there are important types of data:
 *     {@link Client}, {@link Article}, {@link Faktura} and {@link FakturaItem}.
 *     For each of those there are operating classes
 *     ({@link ClientOperations}, {@link ArticleOperations},
 *     {@link FakturaOperations} and {@link FakturaItemOperations}),
 *     data access object interfaces
 *     ({@link ClientDao}, {@link ArticleDao},
 *     {@link FakturaDao} and {@link FakturaItemDao}).
 *     Application does not have "proper" database
 *     but a mocking object implementation using lists with classes:
 *     {@link ClientMemoryDao}, {@link ArticleMemoryDao},
 *     {@link FakturaMemoryDao} and {@link FakturaItemMemoryDao}.
 *     In <code>InvoiceApplication</code> we start
 *     our "databases", operational classes and menu.
 * <p/>

 * @version     19 November 2020
 * @author      Tobiasz Wojnar
 */
public class InvoiceApplication {
    //TODO unit test
    //TODO test adding and deleting items

    //TODO javadoc MemoryDao
    //TODO javadoc item
    //TODO javadoc Operators

    //TODO UML

    //TODO should add be in menu or Operation?

    /**
     * Creates mocking object databases, GUI and operational classes.

     * @param args is not used.
     */
    public static void main(String[] args) { //NOPMD

        ClientDao clientDao = new ClientMemoryDao();
        ArticleDao articleDao = new ArticleMemoryDao();
        FakturaItemDao fakturaItemDao = new FakturaItemMemoryDao();
        FakturaDao fakturaDao = new FakturaMemoryDao();

        ClientOperations clientOperations = new ClientOperations(clientDao);
        ArticleOperations articleOperations = new ArticleOperations(articleDao);
        FakturaItemOperations fakturaItemOperations =
                new FakturaItemOperations(fakturaItemDao, articleDao);
        FakturaOperations fakturaOperations = new FakturaOperations(fakturaDao, clientDao, fakturaItemDao);

        CommandLineMenu commandLineMenu = new CommandLineMenu(
                clientOperations, articleOperations, fakturaOperations, fakturaItemOperations);

        commandLineMenu.run();

    }
}
