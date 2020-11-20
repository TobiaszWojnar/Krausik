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
public class Client {

    private final String clientId;
    private final String name;
    private final String nip;
    private final String address;
    private final String city;
    private final String postal;


    public Client(
            String clientId, String name, String nip, String address, String city, String postal
    ) { //NOPMD
        this.clientId = clientId;
        this.name = name;
        this.nip = nip;
        this.address = address;
        this.city = city;
        this.postal = postal;
    }

    public String getName() {
        return name;
    }

    public String getNip() {
        return nip;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public String getId() {
        return clientId;
    }

}
