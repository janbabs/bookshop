package com.janbabs.bookshop.transport;

import com.janbabs.bookshop.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserEditDTO {
    private Long id;
    private String login;
    @Size(max = 20, min = 2, message = "Imie musi zawierać przynajmniej 2 znaki")
    private String firstName;
    @Size(max = 20, min = 2, message = "Nazwisko musi zawierać przynajmniej 2 znaki")
    private String lastName;
    @NotNull
    @Email(message = "Niepoprawny format maila")
    private String email;

    public UserEditDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }


}
