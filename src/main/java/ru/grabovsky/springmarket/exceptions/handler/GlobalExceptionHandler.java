package ru.grabovsky.springmarket.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.grabovsky.market.api.dto.MessageDto;
import ru.grabovsky.market.api.dto.ValidationError;
import ru.grabovsky.market.api.dto.ValidationErrorResponseDto;
import ru.grabovsky.springmarket.exceptions.RoleNotFoundException;
import ru.grabovsky.springmarket.exceptions.UserAlreadyExistsException;
import ru.grabovsky.springmarket.exceptions.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            RoleNotFoundException.class,
            UserAlreadyExistsException.class,
            BadCredentialsException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto onRegisterOtAuthException(RuntimeException e){
        log.debug("Catch exception " + e.getClass().getSimpleName() );
        return new MessageDto(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationErrorResponseDto onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponseDto(errors);
    }

}
