package db_service.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * @author IvanLis
 * @version 1.0
 * @since 03/01/2016.
 * <p>
 * mysql_ex
 */
public abstract class AbstractDao<T extends Serializable> {
    private Class<T> clazz;

    public final void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }


    public T getEntity(int id) {
        T result = null;
        try {
            Session session = DBService.getSessionFactory().openSession();
            result = (T) session.get(clazz, id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addEntity(T entity) {
        try {
            Session session = DBService.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEntity(T entity) {
        try {
            Session session = DBService.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEntity(T entity) {
        try {
            Session session = DBService.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEntityById(int id) {
        Session session = DBService.getSessionFactory().openSession();
        T entity = getEntity(id);
        deleteEntity(entity);
    }

    public List<T> getEntities() {
        List<T> entities = null;
        try {
            Session session = DBService.getSessionFactory().openSession();
            entities =  (List<T>)session.createCriteria(clazz).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
}
