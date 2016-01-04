package db_service.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author IvanLis
 * @version 1.0
 * @since 02/01/2016.
 * <p>
 * mysql_ex
 */

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int book_id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private int isbn;

    @Column(name = "description")
    private String description;
    @ManyToMany (mappedBy = "books")
//    @JoinTable(name = "book_authors",
//            joinColumns = {@JoinColumn(name = "book_id")},
//            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    Set<Author> authors;

    public Book() {
    }

    public Book(String title, String description, int isbn) {
        this.title = title;
        this.isbn = isbn;
        this.description = description;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }


    public Set<Author> getAuthors() {
        if (authors == null)
            authors = new HashSet<>();
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Id: " + book_id + "\nTitle: " + title + "\nDescription: " + description + "\nIsbn: " + isbn;
    }
}
