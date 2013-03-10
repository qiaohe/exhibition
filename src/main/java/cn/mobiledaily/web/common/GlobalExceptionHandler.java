package cn.mobiledaily.web.common;

import cn.mobiledaily.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException pe) {
        return new ResponseEntity<String>(pe.getBindingResult().getAllErrors().toString(), HttpStatus.BAD_REQUEST);
    }
}
