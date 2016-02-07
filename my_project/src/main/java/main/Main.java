package main;

import db_service.dao.DBService;
import db_service.dao.implementation.AuthorDaoImpl;
import db_service.dao.implementation.BookDaoImpl;
import db_service.tables.Author;
import db_service.tables.Book;
import org.hibernate.Session;

/**
 * @author IvanLis
 * @version 1.0
 * @since 03/01/2016.
 * <p/>
 * my_project
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = Factory.getInstance();
        BookDaoImpl bookDao = factory.getBookDao();
        AuthorDaoImpl authorDao = factory.getAuthorDao();
        bookDao.setClazz(Book.class);
        authorDao.setClazz(Author.class);
        Author leoTolstoy = new Author("Leo", "Tolstoy", "Russia");
        Author nikolayGogol = new Author("Nikolai", "Gogol", "Russia");
        Book warPeace = new Book("War and Peace", "Book about war with Napoleon in Russia", 3421);
        Book nightsInDicanka = new Book("Nights in Dikanka", "Horror", 3123);

        authorDao.addEntity(leoTolstoy);
        authorDao.addEntity(nikolayGogol);
        authorDao.addBook(authorDao.getEntity(1), warPeace);
        authorDao.addBook(authorDao.getEntity(2), nightsInDicanka);


//        Author author = authorDao.getEntity(1);
//        Author author1 = authorDao.getEntity(2);
//        Book book = bookDao.getEntity(1);
//        Book book1 = bookDao.getEntity(2);
//        authorDao.addBook(author,book);
//        authorDao.addBook(author1, book1);


        System.out.println(bookDao.getEntities());
        System.out.println("\n\n\n");
        System.out.println(authorDao.getEntities());
    }
}
