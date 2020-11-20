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
public class ClientOperations {

    private final ClientDao clientDao;

    public ClientOperations(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void add(BufferedReader reader) {
        try {
            System.out.println("Add client");
            System.out.println("Enter a name:");
            final String clientName = reader.readLine(); //TODO why PMD wants it final?
            System.out.println("Enter a id:");
            String clientId = reader.readLine();
            if (clientDao.checkIfInExist(clientId)) {
                System.out.println("Id exist in database. Addition unsuccessful");
            } else {
                System.out.println("Enter nip (10-digit number):");
                String nip = reader.readLine();
                System.out.println("Enter address:");
                String address = reader.readLine();
                System.out.println("Enter city:");
                String city = reader.readLine();
                System.out.println("Enter postal code:");
                String postal = reader.readLine();
                clientDao.add(new Client(clientId, clientName, nip, address, city, postal));
                System.out.println("addition successful");
            }
        } catch (Exception e) {
            System.out.println("Unsupported datatype - " + e.getMessage());
            System.out.println("Addition unsuccessful");
        }
    }

    public void delete(String personId) {
        clientDao.delete(personId);
    }

    public static void print(Client person) {
        System.out.println(person.getName());
        System.out.println("NIP\t" + person.getNip());
        System.out.println("Street\t" + person.getAddress());
        System.out.println("City\t" + person.getCity() + "\tPostal Code\t" + person.getPostal());
    }

    public void print(String personId) {
        Client person = clientDao.get(personId);
        print(person);
    }

    public void printAll() {
        for (Client person : clientDao.getAll()) {
            print(person);
        }
    }
}
