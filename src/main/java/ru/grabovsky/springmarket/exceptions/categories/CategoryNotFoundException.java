package ru.grabovsky.springmarket.exceptions.categories;

/**
 * CategoryNotFoundException
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 16:30
 */
public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
