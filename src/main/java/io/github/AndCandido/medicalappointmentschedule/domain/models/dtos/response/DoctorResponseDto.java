package io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record DoctorResponseDto(
    UUID id,

    String name,

    String nickname,

    String phone,

    List<AppointmentResponseDto> appointments,

    LocalDateTime createdAt
) {
}
