package tr.com.obss.jss.week3spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class Book extends EntityBase {

    @Column(name = "TITLE", length = 255)
    private String title;

    @Column(name = "ISBN", length = 255, unique = true)
    private long isbn;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    @JsonManagedReference
    private Author author;

    @JsonBackReference
    @ManyToMany(mappedBy = "readList")
    private List<User> readUserList;

    @JsonBackReference
    @ManyToMany(mappedBy = "favoritesList")
    private List<User> favUserList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<User> getFavUserList() {
        return favUserList;
    }


    public List<User> getReadUserList() {
        return readUserList;
    }

    public void setReadUserList(List<User> readList) {
        this.readUserList = readList;
    }

    public void setFavUserList(List<User> favUserList) {
        this.favUserList = favUserList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return isbn == book.isbn;
    }

    @Override
    public int hashCode() {
        return (int) (isbn ^ (isbn >>> 32));
    }
}
