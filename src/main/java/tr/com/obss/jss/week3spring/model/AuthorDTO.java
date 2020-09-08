package tr.com.obss.jss.week3spring.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir kitap ismi giriniz")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
