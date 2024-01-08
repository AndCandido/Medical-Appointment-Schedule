package io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request;

import io.github.AndCandido.medicalappointmentschedule.domain.enums.AgeGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequestDto(

    @NotBlank
    String name,

    @Email
    String email,

    @NotBlank
    String contactPhone,

    @NotNull
    AgeGroup ageGroup
) {
}
