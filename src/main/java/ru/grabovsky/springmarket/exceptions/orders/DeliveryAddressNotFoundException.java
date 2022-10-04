package ru.grabovsky.springmarket.exceptions.orders;

/**
 * AddressNotFoundException
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 13:40
 */
public class DeliveryAddressNotFoundException extends RuntimeException{
    public DeliveryAddressNotFoundException(String message) {
        super(message);
    }
}
