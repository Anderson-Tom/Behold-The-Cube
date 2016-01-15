package db_service.dao.implementation;

import db_service.dao.AbstractDao;
import db_service.dao.DBService;
import db_service.tables.Author;
import db_service.tables.Book;
import org.hibernate.Session;

/**
 * @author IvanLis
 * @version 1.0
 * @since 02/01/2016.
 * <p/>
 * mysql_ex
 */
public class BookDaoImpl extends AbstractDao<Book> {
    public BookDaoImpl() {}

    public void addAuthor (Author author, Book book) {
        Session session = DBService.getSessionFactory().openSession();
        book = (Book) session.get(book.getClass(), book.getBook_id());
        book.addAuthor(author);
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
        session.close();
    }

}
