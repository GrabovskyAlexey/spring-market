package ru.grabovsky.springmarket.exceptions.orders;

import org.springframework.security.access.AccessDeniedException;

/**
 * OrderAccessDeniedException
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 12:12
 */
public class OrderAccessDeniedException extends AccessDeniedException {
    public OrderAccessDeniedException(String msg) {
        super(msg);
    }
}
