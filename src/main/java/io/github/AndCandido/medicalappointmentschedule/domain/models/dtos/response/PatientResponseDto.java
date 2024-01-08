package io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response;

import io.github.AndCandido.medicalappointmentschedule.domain.enums.AgeGroup;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record PatientResponseDto(
    UUID id,

    String name,

    String email,

    String contactPhone,

    AgeGroup ageGroup,

    List<AppointmentResponseDto> appointments,

    LocalDateTime createdAt
) {
}
