package ru.grabovsky.springmarket.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * IllegalJsonFieldValueException
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 18:03
 */

@Getter
@RequiredArgsConstructor
public class IllegalJsonFieldValueException extends RuntimeException{
    private final String filedName;
    private final String errorMessage;
}
