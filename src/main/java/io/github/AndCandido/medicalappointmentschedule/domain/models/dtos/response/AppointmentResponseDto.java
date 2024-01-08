package io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response;

import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Doctor;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record AppointmentResponseDto (
    UUID id,

    LocalDate appointmentDate,

    LocalTime appointmentTime,

    DoctorResponseDto doctor,

    PatientResponseDto patient,

    LocalDateTime createdAt
) {
}
