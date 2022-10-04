package ru.grabovsky.springmarket.exceptions.orders;

/**
 * OrderNotFoundException
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 12:05
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
