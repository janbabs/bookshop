package com.janbabs.bookshop.transport;

import com.janbabs.bookshop.adnotation.ExistingLogin;
import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserTO {

    private Long id;
    @NotNull
    @Size(max = 20, min = 5, message = "Nazwa użytkownika musi zawierać przynajmniej 5 znakow, a maksymalnie 20")
    private String login;
    @NotNull
    @Size(min = 8, message = "Hasło musi zawiarać przynajmniej 8 znaków")
    private String password;
    @Size(max = 20, min = 2,message = "Imie musi zawierać przynajmniej 2 znaki")
    private String firstName;
    @Size(max = 20, min = 2,message = "Nazwisko musi zawierać przynajmniej 2 znaki")
    private String lastName;
    @NotNull
    @Email(message = "Niepoprawny format maila")
    private String email;
    private userType userType;

    public UserTO() {
    }

    public UserTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public UserTO(@NotNull String login, @NotNull @Size(min = 8, message = "Hasło musi zawiarać przynajmniej 8 znaków") String password, @Size(max = 20, min = 2, message = "Imie musi zawierać przynajmniej 2 znaki") String firstName, @Size(max = 20, min = 2, message = "Nazwisko musi zawierać przynajmniej 2 znaki") String lastName, @NotNull @Email(message = "Niepoprawny format maila") String email, com.janbabs.bookshop.domain.userType userType) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public com.janbabs.bookshop.domain.userType getUserType() {
        return userType;
    }

    public void setUserType(com.janbabs.bookshop.domain.userType userType) {
        this.userType = userType;
    }
}
