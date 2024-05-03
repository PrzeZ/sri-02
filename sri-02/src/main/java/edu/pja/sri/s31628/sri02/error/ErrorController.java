package edu.pja.sri.s31628.sri02.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


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

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> handleNotSupportedMethod(Exception ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.METHOD_NOT_ALLOWED)
                .error("Method not supported")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = { NoResourceFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .error("Not Found")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}