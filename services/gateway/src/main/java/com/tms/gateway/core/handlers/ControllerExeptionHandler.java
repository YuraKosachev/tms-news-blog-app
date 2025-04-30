package com.tms.gateway.core.handlers;

import com.tms.gateway.core.models.error.ErrorMessage;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExeptionHandler {

        @ExceptionHandler(FeignException.class)
        public ResponseEntity<ErrorMessage> resourceNotFoundException(FeignException ex, WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    LocalDateTime.now(),
                    ex.getMessage(),
                    request.getDescription(false));

            return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
        }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> resourceException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
