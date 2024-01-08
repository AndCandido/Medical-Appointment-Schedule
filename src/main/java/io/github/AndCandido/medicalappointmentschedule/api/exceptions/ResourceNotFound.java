package io.github.AndCandido.medicalappointmentschedule.api.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
