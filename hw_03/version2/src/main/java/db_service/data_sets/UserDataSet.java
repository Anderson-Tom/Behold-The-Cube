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
//    private static final long serialVersionUID = -5706689714326132798L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String name;

    @Column(name = "pass")
    private String pass;

    @Column(name = "email", unique = true)
    private String email;

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
    }

    public UserDataSet(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
