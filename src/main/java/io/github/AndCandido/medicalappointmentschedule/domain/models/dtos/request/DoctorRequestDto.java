package io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record DoctorRequestDto(

    @NotBlank
    String name,

    @NotBlank
    String nickname,

    @NotBlank
    String phone
) {
}
