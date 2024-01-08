package io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentRequestDto(

    @NotNull
    LocalDate appointmentDate,

    @NotNull
    LocalTime appointmentTime,

    @NotNull
    UUID doctorId,

    @NotNull
    UUID patientId
) {
}
