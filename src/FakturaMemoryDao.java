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
public class FakturaMemoryDao implements FakturaDao {

    private final List<Faktura> fakturaArrayList;

    public FakturaMemoryDao() {
        fakturaArrayList = new ArrayList<>();
    }

    @Override
    public void delete(String fakturaId) {
        fakturaArrayList.remove(get(fakturaId));
    }

    @Override
    public String add(Faktura faktura) {
        fakturaArrayList.add(faktura);
        return faktura.getId();
    }

    @Override
    public Faktura get(String fakturaId) {
        Faktura fakturaTemp = null;
        for (Faktura faktura : fakturaArrayList) {
            if (faktura.getId().equals(fakturaId)) {
                fakturaTemp = faktura;
            }
        }
        return fakturaTemp;
    }

    @Override
    public List<Faktura> getAll() {
        return fakturaArrayList;
    }

    @Override
    public List<Faktura> getAllForClient(String clientId) {
        List<Faktura> tempList = new ArrayList<>();
        for (Faktura faktura : fakturaArrayList) {
            if (faktura.getBuyerId().equals(clientId)) {
                tempList.add(faktura);
            }
        }
        return tempList;
    }

    @Override
    public boolean checkIfInExist(String fakturaId) {
        boolean exist = false;
        for (Faktura faktura : fakturaArrayList) {
            if (faktura.getFakturaId().equals(fakturaId)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
