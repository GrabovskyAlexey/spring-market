package ru.grabovsky.springmarket.exceptions.products;

/**
 * ProductNotFoundException
 *
 * @author grabovsky.alexey
 * @created 17.09.2022 10:21
 */
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
