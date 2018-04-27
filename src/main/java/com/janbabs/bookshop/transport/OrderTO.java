package com.janbabs.bookshop.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTO {
//    @Pattern(regexp = "^([0+]48)?\\d{9}$",message = "Niewłaściwy format numeru telefonu")
    private String phonenumber;
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}",message = "Nieprawidłowy format kodu pocztowego. XX-XXX")
    private String zipcode;
    private String street;
    private String city;
}
