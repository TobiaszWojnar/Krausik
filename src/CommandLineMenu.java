import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The <code>Menu</code> class is command line menu
 * for simple application issuing invoices (Polish: <b>Faktura</b>).
 * It is exercise for object oriented programing course in Java
 * at Wroclaw University of Science and Technology.
 * The objective of this list is to implement
 * simple application issuing invoices with GRASP methodology,
 * getting familiar with PMD and Checkstyle plugins
 * and generate UML class diagram.
 *
 * <p><code>Menu</code> intakes commands form standard input
 *     and forwards them to classes that are executing them.
 *     It enables <code>print, add, delete</code>
 *     clients, articles, invoices and items in invoices.
 *     With private methods for submenus it forwards commands to
 *     {@link ClientOperations}, {@link ArticleOperations},
 *     {@link FakturaOperations} and {@link FakturaItemOperations}.
 * <p/>

 * @version     19 November 2020
 * @author      Tobiasz Wojnar
 */

public class CommandLineMenu { //NOPMD

    /**
     * Operational class that forwards commands form menu to database.
     */
    private final ClientOperations clientOperations;
    /**
     * Operational class that forwards commands form menu to database.
     */
    private final ArticleOperations articleOperations;
    /**
     * Operational class that forwards commands form menu to database.
     */
    private final FakturaOperations fakturaOperations;
    /**
     * Operational class that forwards commands form menu to database.
     */
    private final FakturaItemOperations fakturaItemOp;
    /**
     * There is one reader for all application.
     * In constructor it is initialized to read from standard input.
     */
    private final BufferedReader reader;

    /**
     * Class constructor specifying number of objects to create.

     * @param clientOperations
     * responsible for contact with DAO for clients "database".

     * @param articleOperations
     * responsible for contact with DAO for articles "database".

     * @param fakturaOperations
     * responsible for contact with DAO for faktura - invoice "database".

     * @param fakturaItemOp
     * responsible for contact with DAO for items in faktura-invoice "database".
     */
    public CommandLineMenu(
            final ClientOperations clientOperations,
            final ArticleOperations articleOperations,
            final FakturaOperations fakturaOperations,
            final FakturaItemOperations fakturaItemOp) {
        //TODO why PMD wanted me to make final parameters?
        this.clientOperations = clientOperations;
        this.articleOperations = articleOperations;
        this.fakturaOperations = fakturaOperations;
        this.fakturaItemOp = fakturaItemOp;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Creates simple command line menu.
     * In infinite loop it enters submenus for each type of object in the project
     * ({@link Article}, {@link Client}, {@link Faktura} and {@link FakturaItem}).
     * <code>exit</code> command enables to stop it.
     */
    public void run() {
        String line;

        mainMenu:
        while (true) {
            try {
                System.out.println(
                        "Select command form "
                        + "{'client', 'article', 'faktura', 'help' 'exit'}");
                line = reader.readLine();

                switch (line) { //TODO can make map from string to lambda expression
                    case "exit":
                        break mainMenu;
                    case "help":
                        System.out.println("List of available commands:");
                        System.out.println("client");
                        System.out.println("article");
                        System.out.println("faktura");
                        break;
                    case "client":
                        clientMenu();
                        break;
                    case "article":
                        articleMenu();
                        break;
                    case "faktura":
                        fakturaMenu();
                        break;
                    default:
                        System.out.println("Unsupported command\t'" + line + "'");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Unsupported command\n" + e.getMessage());
            }
        }
    }

    private void clientMenu() {
        String line;

        peopleMenu:
        while (true) {
            try {
                System.out.println("Select command form {'add', 'delete', 'show', 'exit'}");
                line = reader.readLine();
                switch (line) {
                    case "exit":
                        break peopleMenu;
                    case "add":
                        clientOperations.add(reader);
                        break;
                    case "delete":
                        System.out.println("Delete person");
                        System.out.println("Enter a id:");
                        clientOperations.delete(reader.readLine());
                        break;
                    case "show":
                        System.out.println(
                                "Type person id to show all info about and all their faktura "
                                + "or press enter to show info about all people");
                        System.out.println("Enter a id:");
                        line = reader.readLine();
                        if ("".equals(line)) {
                            clientOperations.printAll();
                        } else {
                            clientOperations.print(line);
                            fakturaOperations.printAllFakturaForClient(line);
                        }
                        break;
                    default:
                        System.out.println("unsupported command\t'" + line + "'");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Unsupported command\n" + e.getMessage());
            }
        }
    }

    private void articleMenu() {
        String line;

        articleMenu:
        while (true) {
            try {
                System.out.println(
                        "Select command form {'add', 'delete', 'show', 'exit'}");
                line = reader.readLine();
                switch (line) {
                    case "exit":
                        break articleMenu;
                    case "add":
                        articleOperations.add(reader);
                        break;
                    case "delete":
                        System.out.println("Delete article\nEnter a id:");
                        articleOperations.delete(reader.readLine());
                        break;
                    case "show":
                        System.out.println("Type Article id or press enter to show all.");
                        System.out.println("Enter a id:");
                        line = reader.readLine();
                        if ("".equals(line)) {
                            articleOperations.printAll();
                        } else {
                            articleOperations.print(line);
                        }
                        break;
                    default:
                        System.out.println("Unsupported command\t'" + line + "'");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Unsupported command\n" + e.getMessage());
            }
        }
    }

    private void fakturaMenu() {
        String line;

        articleMenu:
        while (true) {
            try {
                System.out.println("Select command form {'add', 'delete', 'show', 'edit', 'exit'}");
                line = reader.readLine();
                switch (line) {
                    case "exit":
                        break articleMenu;
                    case "add":
                        fakturaOperations.add(reader);
                        break;
                    case "delete":
                        System.out.println("Delete faktura\nEnter a id:");
                        fakturaOperations.delete(reader.readLine());
                        break;
                    case "show":
                        System.out.println("Type faktura id to show  or press enter to show all");
                        System.out.println("Enter a id:");
                        line = reader.readLine();
                        if ("".equals(line)) {
                            fakturaOperations.printAll();
                        } else {
                            fakturaOperations.print(line);
                        }
                        break;
                    case "edit":
                        System.out.println("Enter faktura id:");
                        line = reader.readLine();
                        if (fakturaOperations.checkIfInExist(line)) {
                            fakturaEditMenu(line);
                        } else {
                            System.out.println("Id not exist in database.");
                        }
                        break;
                    default:
                        System.out.println("Unsupported command\t'" + line + "'");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Unsupported command\n" + e.getMessage());
            }
        }
    }

    private void fakturaEditMenu(final String fakturaId) {
        String line;

        fakturaEditMenu:
        while (true) {
            try {
                System.out.println("Editing items in faktura.");
                System.out.println("Select command form {'add', 'delete', 'show', 'exit'}");
                line = reader.readLine();
                switch (line) {
                    case "exit":
                        break fakturaEditMenu;
                    case "add":
                        System.out.println("Adding new item");
                        fakturaItemOp.add(fakturaId, reader);
                        break;
                    case "delete":
                        System.out.println("Deleting item\n Enter id");
                        fakturaItemOp.delete(reader.readLine());
                        break;
                    case "show":
                        System.out.println("Printing faktura");
                        fakturaOperations.print(fakturaId);
                        break;
                    default:
                        System.out.println("Unsupported command\t'" + line + "'");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Unsupported command\n" + e.getMessage());
            }
        }
    }
}
