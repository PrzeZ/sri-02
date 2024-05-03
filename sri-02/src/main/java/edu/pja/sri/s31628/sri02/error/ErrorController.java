package edu.pja.sri.s31628.sri02.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = { IllegalArgumentException.class, MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorMessage> handleBadRequestException(Exception ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error("Bad request")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}