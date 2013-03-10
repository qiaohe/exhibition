package cn.mobiledaily.web.common;

import cn.mobiledaily.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/9/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    ResponseEntity<String> handleException(EntityNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({Exception.class})
    ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List> handleValidationException(MethodArgumentNotValidException mae) {
        List<String> errors = new ArrayList<>();
        for (ObjectError err : mae.getBindingResult().getAllErrors()) {
            errors.add(getErrorString(err));
        }
        return new ResponseEntity<List>(errors, HttpStatus.BAD_REQUEST);
    }

    private String getErrorString(ObjectError error) {
        String fn = error.getObjectName();
        if (error instanceof FieldError) {
            fn = ((FieldError) error).getField();
        }
        return String.format("%s:%s", fn, error.getDefaultMessage());
    }
}
