package com.janbabs.bookshop.transport;

import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotNull
    @Size(max = 20, min = 5, message = "Nazwa użytkownika musi zawierać przynajmniej 5 znakow, a maksymalnie 20")
    private String login;
    @NotNull
    @Size(min = 8, message = "Hasło musi zawiarać przynajmniej 8 znaków")
    private String password;
    @Size(max = 20, min = 2, message = "Imie musi zawierać przynajmniej 2 znaki")
    private String firstName;
    @Size(max = 20, min = 2, message = "Nazwisko musi zawierać przynajmniej 2 znaki")
    private String lastName;
    @NotNull
    @Email(message = "Niepoprawny format maila")
    private String email;
    private userType userType;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userType = user.getUserType();
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email, userType userType) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }
}
