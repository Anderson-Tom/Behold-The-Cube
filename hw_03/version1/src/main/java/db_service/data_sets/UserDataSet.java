package db_service.data_sets;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * hw-03
 */

@SuppressWarnings("UnusedDeclaration")
public class UserDataSet {
    private long id;
    private String name;

    public UserDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User Data Set { id: " + id + ", name: " + name + " }";
    }
}
