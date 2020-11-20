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
public class ClientMemoryDao implements ClientDao {
    private final List<Client> clientList;

    public ClientMemoryDao() {
        clientList = new ArrayList<>();
    }

    @Override
    public void delete(String clientId) {
        clientList.remove(get(clientId));
    }

    @Override
    public String add(Client client) {
        clientList.add(client);
        return client.getId();
    }

    @Override
    public Client get(String clientId) {
        for (Client person : clientList) {
            if (person.getId().equals(clientId)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        return clientList;
    }

    @Override
    public boolean checkIfInExist(String clientId) {
        boolean exist = false;
        for (Client client : clientList) {
            if (client.getId().equals(clientId)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
