package io.github.AndCandido.medicalappointmentschedule.api.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class ApiResponseError {
    private List<String> messages;
    private int code;
    private String error;
    private LocalDateTime timestamp;

    public ApiResponseError(List<String> message) {
        this.messages = message;
        this.code = HttpStatus.BAD_REQUEST.value();
        this.error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponseError(String message) {
        this.messages = Collections.singletonList(message);
        this.code = HttpStatus.BAD_REQUEST.value();
        this.error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponseError(String message, HttpStatus httpStatus) {
        this.messages = Collections.singletonList(message);
        this.code = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.timestamp = LocalDateTime.now();
    }
}
