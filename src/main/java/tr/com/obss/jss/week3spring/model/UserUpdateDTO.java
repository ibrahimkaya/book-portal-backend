package tr.com.obss.jss.week3spring.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateDTO {

    @NotBlank
    @Size(max = 255, min = 3, message = "lütfen geçerli bir password giriniz")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
