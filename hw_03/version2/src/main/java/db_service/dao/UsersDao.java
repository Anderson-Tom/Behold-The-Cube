package db_service.dao;

import db_service.data_sets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.List;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p>
 * version2
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserDataSet getById(long id) throws HibernateException {
        return (UserDataSet) session.get(UserDataSet.class, id);
    }

    public UserDataSet getByName(String name) throws HibernateException {
        BigInteger bigInt =  (BigInteger) session.createSQLQuery("SELECT id FROM users WHERE login ='" + name +"'").uniqueResult();
        if (bigInt == null){
            return null;
        }
        return getById(bigInt.longValue());
    }


    public List<UserDataSet> listAll() throws HibernateException {
        return (List<UserDataSet>) session.createCriteria(UserDataSet.class).list();
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public void insertUser(String name, String pass, String email) throws HibernateException {
        session.save(new UserDataSet(name, pass, email));
    }


}
