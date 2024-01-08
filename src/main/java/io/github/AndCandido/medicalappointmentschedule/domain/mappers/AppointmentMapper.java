package io.github.AndCandido.medicalappointmentschedule.domain.mappers;

import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.AppointmentResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.DoctorResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.PatientResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;

import java.util.List;

public final class AppointmentMapper {
    private AppointmentMapper() {
    }

    public static AppointmentResponseDto toResponseDto(Appointment appointment) {
        if(appointment == null) return null;

        DoctorResponseDto doctorResponseDto =
            DoctorMapper.toResponseWithoutAssociationsDto(appointment.getDoctor());
        PatientResponseDto patientResponseDto =
            PatientMapper.toResponseWithoutAssociationsDto(appointment.getPatient());


        return AppointmentResponseDto.builder()
            .id(appointment.getId())
            .appointmentDate(appointment.getAppointmentDate())
            .appointmentTime(appointment.getAppointmentTime())
            .doctor(doctorResponseDto)
            .patient(patientResponseDto)
            .createdAt(appointment.getCreatedAt())
            .build();
    }

    private static AppointmentResponseDto toResponseWithoutAssociationsDto(Appointment appointment) {
        return appointment == null ? null
            : AppointmentResponseDto.builder()
            .id(appointment.getId())
            .appointmentDate(appointment.getAppointmentDate())
            .appointmentTime(appointment.getAppointmentTime())
            .createdAt(appointment.getCreatedAt())
            .build();
    }

    public static List<AppointmentResponseDto> toResponseDtoList(List<Appointment> appointmentList) {
        return appointmentList == null ? null
            : appointmentList.stream().map(AppointmentMapper::toResponseDto).toList();
    }

    public static List<AppointmentResponseDto> toResponseWithoutAssociationsDtoList(List<Appointment> appointmentList) {
        return appointmentList == null ? null
            : appointmentList.stream().map(AppointmentMapper::toResponseWithoutAssociationsDto).toList();
    }
}
