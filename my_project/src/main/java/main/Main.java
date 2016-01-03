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

//        bookDao.addEntity(new Book("War and Peace", "Bestseller: book about war with Napoleon in Russia", 3421));
//        authorDao.addEntity(new Author("Nikolai", "Gogol", "Russia"));
//        authorDao.getEntity(1).getBooks().add(bookDao.getEntity(1));
    }
}
