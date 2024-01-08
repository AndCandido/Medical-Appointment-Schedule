package io.github.AndCandido.medicalappointmentschedule.domain.mappers;

import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.AppointmentResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.PatientResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;

import java.util.List;

public final class PatientMapper {
    private PatientMapper() {}

    public static PatientResponseDto toResponseDto(Patient patient) {
        if(patient == null) return null;

        List<Appointment> appointmentList = patient.getAppointmentList();
        List<AppointmentResponseDto> appointmentResponseDtoList =
                AppointmentMapper.toResponseWithoutAssociationsDtoList(appointmentList);

        return PatientResponseDto.builder()
            .id(patient.getId())
            .name(patient.getName())
            .email(patient.getEmail())
            .contactPhone(patient.getContactPhone())
            .ageGroup(patient.getAgeGroup())
            .appointments(appointmentResponseDtoList)
            .createdAt(patient.getCreatedAt())
            .build();
    }

    public static PatientResponseDto toResponseWithoutAssociationsDto(Patient patient) {
        if(patient == null) return null;

        return PatientResponseDto.builder()
            .id(patient.getId())
            .name(patient.getName())
            .email(patient.getEmail())
            .contactPhone(patient.getContactPhone())
            .ageGroup(patient.getAgeGroup())
            .createdAt(patient.getCreatedAt())
            .build();
    }

    public static List<PatientResponseDto> toResponseDtoList(List<Patient> patients) {
        return patients == null ? null
            : patients.stream().map(PatientMapper::toResponseDto).toList();
    }
}
