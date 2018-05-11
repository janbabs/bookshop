package com.janbabs.bookshop.transport;

import com.janbabs.bookshop.domain.CartItem;
import com.janbabs.bookshop.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CartDTO {
    private Long id;
    private User user;
    private List<CartItem> cartItems;

}
