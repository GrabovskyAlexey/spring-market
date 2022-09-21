package ru.grabovsky.springmarket.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.grabovsky.market.api.dto.util.MessageDto;
import ru.grabovsky.market.api.dto.util.ValidationError;
import ru.grabovsky.market.api.dto.util.ValidationErrorResponseDto;
import ru.grabovsky.springmarket.exceptions.IllegalJsonFieldValueException;
import ru.grabovsky.springmarket.exceptions.user.RoleNotFoundException;
import ru.grabovsky.springmarket.exceptions.user.UserAlreadyExistsException;
import ru.grabovsky.springmarket.exceptions.user.UserNotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
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
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponseDto onConstraintViolationException(ConstraintViolationException e) {
        List<ValidationError> errors = e.getConstraintViolations().stream()
                .map(
                        violation -> new ValidationError(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());;
        return new ValidationErrorResponseDto(errors);
    }

    @ExceptionHandler(IllegalJsonFieldValueException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationErrorResponseDto onIllegalJsonFieldValueException(IllegalJsonFieldValueException e) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError(e.getFiledName(), e.getErrorMessage()));
        return new ValidationErrorResponseDto(errors);
    }

}
