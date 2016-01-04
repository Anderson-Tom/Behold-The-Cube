package db_service.dao.implementation;

import db_service.dao.AbstractDao;
import db_service.dao.DBService;
import db_service.tables.Author;
import db_service.tables.Book;
import org.hibernate.Session;

/**
 * @author IvanLis
 * @version 1.0
 * @since 03/01/2016.
 * <p>
 * mysql_ex
 */
public class AuthorDaoImpl extends AbstractDao<Author> {

    public void addBook(Author author, Book book) {
        Session session =  DBService.getSessionFactory().openSession();
        author = (Author) session.get(author.getClass(), author.getAuthor_id());
        author.addBook(book);
        session.beginTransaction();
        session.update(author);
        session.getTransaction().commit();
        session.close();
    }

}
