package tr.com.obss.jss.week3spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity 
@Table(name = "AUTHOR")
public class Author extends EntityBase{
    
    @Column(name = "NAME", unique = true)
    private String name;


    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "author")
    @JsonBackReference
    private Set<Book> books ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
