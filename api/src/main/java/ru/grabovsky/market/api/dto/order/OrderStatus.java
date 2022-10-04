package ru.grabovsky.market.api.dto.order;

/**
 * OrderStatus
 *
 * @author grabovsky.alexey
 * @created 22.09.2022 15:05
 */
public enum OrderStatus {
    CREATED,
    PAID,
    PROCESSED,
    ON_DELIVERY,
    DELIVERED,
    COMPLETE
}
