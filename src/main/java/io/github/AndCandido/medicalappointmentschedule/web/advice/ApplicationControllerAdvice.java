package io.github.AndCandido.medicalappointmentschedule.web.advice;

import io.github.AndCandido.medicalappointmentschedule.api.error.ApiResponseError;
import io.github.AndCandido.medicalappointmentschedule.api.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
            .stream().map(ObjectError::getDefaultMessage).toList();

        ApiResponseError apiResponseError = new ApiResponseError(errors);
        return ResponseEntity.badRequest().body(apiResponseError);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponseError> handlerResourceNotFound(ResourceNotFound ex) {
        ApiResponseError apiResponseError = new ApiResponseError(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseError> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ApiResponseError apiResponseError = new ApiResponseError(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(apiResponseError);
    }
}
