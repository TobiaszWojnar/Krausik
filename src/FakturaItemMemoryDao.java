import java.util.ArrayList;
import java.util.LinkedList;
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
public class FakturaItemMemoryDao implements FakturaItemDao {
    private final List<FakturaItem> allFakturaItems;

    public FakturaItemMemoryDao() {
        allFakturaItems = new ArrayList<>();
    }

    @Override
    public void delete(String fakturaItemId) {
        allFakturaItems.remove(get(fakturaItemId));
    }

    @Override
    public String add(FakturaItem fakturaItem) {
        allFakturaItems.add(fakturaItem);
        return fakturaItem.getFakturaItemId();
    }

    @Override
    public FakturaItem get(String fakturaId) {
        for (FakturaItem fakturaItem : allFakturaItems) {
            if (fakturaItem.getFakturaItemId().equals(fakturaId)) {
                return fakturaItem;
            }
        }
        return null;
    }



    @Override
    public List<FakturaItem> getByFakturaId(String fakturaId) {
        List<FakturaItem> itemsList = new LinkedList<>();
        for (FakturaItem fakturaItem : allFakturaItems) {
            if (fakturaItem.getFakturaId().equals(fakturaId)) {
                itemsList.add(fakturaItem);
            }
        }
        return itemsList;
    }

    @Override
    public void deleteAllForFaktura(String fakturaId) {
        for (FakturaItem fakturaItem : getByFakturaId(fakturaId)) {
            allFakturaItems.remove(fakturaItem);
        }
    }

    @Override
    public boolean checkIfInExist(String fakturaItemId) {
        boolean exist = false;
        for (FakturaItem fakturaItem : allFakturaItems) {
            if (fakturaItem.getFakturaItemId().equals(fakturaItemId)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    @Override
    public String getNextId() {
        int nextId = allFakturaItems.size();
        while (checkIfInExist(String.valueOf(nextId))) {
            nextId++;
        }
        return String.valueOf(nextId);
    }

}
