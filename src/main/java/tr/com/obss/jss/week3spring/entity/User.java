package tr.com.obss.jss.week3spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User extends EntityBase {

    @Column(name = "USERNAME", length = 255, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 255)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private List<Role> roles;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "READ_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private List<Book> readList;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "FAVORITES_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private List<Book> favoritesList;

    public String getUsername() {
        return username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getReadList() {
        return readList;
    }

    public void setReadList(List<Book> readList) {
        this.readList = readList;
    }

    public List<Book> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(List<Book> favoritesList) {
        this.favoritesList = favoritesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
