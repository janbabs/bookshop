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
    @Pattern(regexp = "^([0+]48)?\\d{9}$",message = "Niewłaściwy format numeru telefonu")
    private String phonenumber;
    private String street;
    private String city;
}
