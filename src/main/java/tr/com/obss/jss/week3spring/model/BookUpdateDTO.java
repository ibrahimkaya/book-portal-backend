package tr.com.obss.jss.week3spring.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookUpdateDTO {

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir kitap ismi giriniz")
    private String title;

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir yazar ismi giriniz")
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
