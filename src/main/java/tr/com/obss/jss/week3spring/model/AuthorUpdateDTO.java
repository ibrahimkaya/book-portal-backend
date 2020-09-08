package tr.com.obss.jss.week3spring.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorUpdateDTO {

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir kitap ismi giriniz")
    private String name;

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir kitap ismi giriniz")
    private String newName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
