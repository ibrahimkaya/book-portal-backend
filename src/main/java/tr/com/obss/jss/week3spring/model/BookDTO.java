package tr.com.obss.jss.week3spring.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookDTO {

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir kitap ismi giriniz")
    private String title;

    private long isbn;

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir yazar ismi giriniz")
    private String author;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
