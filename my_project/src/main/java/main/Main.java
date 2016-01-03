package main;

import db_service.dao.implementation.AuthorDaoImpl;
import db_service.dao.implementation.BookDaoImpl;
import db_service.tables.Author;
import db_service.tables.Book;

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
        leoTolstoy.addBook(warPeace);
        nikolayGogol.addBook(nightsInDicanka);
        authorDao.addEntity(leoTolstoy);
        authorDao.addEntity(nikolayGogol);


        System.out.println(bookDao.getEntities());
    }
}
