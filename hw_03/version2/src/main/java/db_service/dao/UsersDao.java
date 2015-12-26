package db_service.dao;

import db_service.data_sets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
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
        return (UserDataSet) session.get(UserDataSet.class, name);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name, String pass) throws HibernateException {
        String login = (String)session.save(new UserDataSet(name, pass));
        long id = getUserId(login);
        return id;
    }
}
