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
public interface ClientDao { //DAO data access object

    void delete(String clientId);

    String add(Client client); //zwraca identyfikator

    Client get(String clientId);

    List<Client> getAll();

    boolean checkIfInExist(String clientId);

}
