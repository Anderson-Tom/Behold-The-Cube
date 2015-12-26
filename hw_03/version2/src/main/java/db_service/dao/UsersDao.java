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

    public UserDataSet get(long id) throws HibernateException {
        return (UserDataSet) session.get(UserDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UserDataSet(name));
    }
}
