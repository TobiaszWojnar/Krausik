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
public class FakturaOperations {

    private final FakturaDao fakturaDao;
    private final ClientDao clientDao;
    private final FakturaItemDao fakturaItemDao;

    public FakturaOperations(
            FakturaDao fakturaDao, ClientDao clientDao, FakturaItemDao fakturaItemDao) {
        this.fakturaDao = fakturaDao;
        this.clientDao = clientDao;
        this.fakturaItemDao = fakturaItemDao;
    }

    public void add(BufferedReader reader) {
        try {
            System.out.println("Add new faktura to database.");
            System.out.println("Enter faktura id:");
            String fakturaId = reader.readLine();
            if (fakturaDao.checkIfInExist(fakturaId)) {
                System.out.println("Id exist in database. Addition unsuccessful");
               } else {
                System.out.println("Enter buyer id:");
                String buyerId = reader.readLine();
                if (clientDao.checkIfInExist(buyerId)) {
                    System.out.println("Enter Enter seller id:");
                    String sellerId = reader.readLine();
                    if (clientDao.checkIfInExist(sellerId)) {
                        fakturaDao.add(new Faktura(fakturaId, sellerId, buyerId));
                        System.out.println("addition successful");
                    } else {
                        System.out.println("Id not exist in database. Addition unsuccessful");
                    }
                } else {
                    System.out.println("Id not exist in database. Addition unsuccessful");
                }
            }
        } catch (Exception e) {
                System.out.println("Unsupported datatype - " + e.getMessage());
                System.out.println("Addition unsuccessful.");
        }

    }

    public void delete(String fakturaId) {
        fakturaDao.delete(fakturaId);
        fakturaItemDao.deleteAllForFaktura(fakturaId);
    }

    public void print (Faktura faktura) {
        System.out.println("Faktura nr\t" + faktura.getFakturaId());
        System.out.println("Date of issue\t" + faktura.getDateOfIssue());
        System.out.println("Seller\n");
        ClientOperations.print(clientDao.get(faktura.getSellerId()));
        System.out.println("Buyer\n");
        ClientOperations.print(clientDao.get(faktura.getBuyerId()));
        System.out.println("\nStatus\t" + faktura.getStatus());

        FakturaItemOperations.printItemsForFaktura(faktura);
    }

    public void print(String fakturaId) {
        Faktura faktura = fakturaDao.get(fakturaId);
        faktura.setFakturaItems(fakturaItemDao.getByFakturaId(fakturaId));
        print(faktura);
    }

    public void printAll() {
        for (Faktura faktura : fakturaDao.getAll()) {
            faktura.setFakturaItems(fakturaItemDao.getByFakturaId(faktura.getFakturaId()));
            print(faktura);
        }
    }

    public void printAllFakturaForClient(String clientId) {
        for (Faktura faktura : fakturaDao.getAllForClient(clientId)) {
            faktura.setFakturaItems(fakturaItemDao.getByFakturaId(faktura.getFakturaId()));
            print(faktura);
        }
    }

    public boolean checkIfInExist(String fakturaId) {
        return fakturaDao.checkIfInExist(fakturaId);
    }

}
