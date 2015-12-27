package db_service.data_sets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */

@Entity
@Table(name = "users")
public class UserDataSet implements Serializable { // Serializable is must have for Hibernate
    private static final long serialVersionUID = -5706689714326132798L;

    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Id
    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "pass", updatable = false)
    private String pass;

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDataSet { Id: " + id + ", Name: " + name + ", Pass: " + pass + " }";
    }
}
