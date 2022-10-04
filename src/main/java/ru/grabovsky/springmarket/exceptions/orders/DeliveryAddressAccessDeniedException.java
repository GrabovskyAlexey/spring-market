package ru.grabovsky.springmarket.exceptions.orders;

import org.springframework.security.access.AccessDeniedException;

/**
 * DeliveryAddressAccessDeniedException
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 19:59
 */
public class DeliveryAddressAccessDeniedException extends AccessDeniedException {
    public DeliveryAddressAccessDeniedException(String msg) {
        super(msg);
    }
}
