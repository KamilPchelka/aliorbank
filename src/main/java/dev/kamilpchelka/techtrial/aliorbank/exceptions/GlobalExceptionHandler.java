package dev.kamilpchelka.techtrial.aliorbank.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.AbstractMap;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(Exception e) {
        log.error("Exception: Unable to process this request.", e);
        AbstractMap.SimpleEntry<String, String> response =
                new AbstractMap.SimpleEntry<>("message", "Unable to process this request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
