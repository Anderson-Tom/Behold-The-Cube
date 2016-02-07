package main;

import db_service.dao.implementation.AuthorDaoImpl;
import db_service.dao.implementation.BookDaoImpl;

/**
 * @author IvanLis
 * @version 1.0
 * @since 03/01/2016.
 * <p/>
 * my_project
 */
public class Factory {

    public static Factory instance;
    public BookDaoImpl bookDao;
    public AuthorDaoImpl authorDao;

    private Factory() {
    }

    static {
        instance = new Factory();
    }

    public static Factory getInstance() {
        return instance;
    }

    public BookDaoImpl getBookDao() {
        if (bookDao == null)
            bookDao = new BookDaoImpl();
        return bookDao;
    }

    public AuthorDaoImpl getAuthorDao() {
        if (authorDao == null)
            authorDao = new AuthorDaoImpl();
        return authorDao;
    }
}
