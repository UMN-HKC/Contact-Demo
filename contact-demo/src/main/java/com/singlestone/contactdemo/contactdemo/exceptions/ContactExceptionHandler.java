package com.singlestone.contactdemo.contactdemo.exceptions;

import com.singlestone.contactdemo.contactdemo.utils.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ContactExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ContactExceptionHandler.class);

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleContactNotFoundException(ContactNotFoundException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }


}
