package io.github.AndCandido.medicalappointmentschedule.domain.mappers;

import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.AppointmentResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.DoctorResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Doctor;

import java.util.List;

public class DoctorMapper {
    public static DoctorResponseDto toResponseDto(Doctor doctor) {
        if(doctor == null) return null;

        List<Appointment> appointmentList = doctor.getAppointmentList();
        List<AppointmentResponseDto> appointmentResponseDtoList =
                AppointmentMapper.toResponseWithoutAssociationsDtoList(appointmentList);

        return DoctorResponseDto.builder()
            .id(doctor.getId())
            .name(doctor.getName())
            .nickname(doctor.getNickname())
            .phone(doctor.getPhone())
            .appointments(appointmentResponseDtoList)
            .createdAt(doctor.getCreatedAt())
            .build();
    }

    public static DoctorResponseDto toResponseWithoutAssociationsDto(Doctor doctor) {
        return doctor == null ? null :
            DoctorResponseDto.builder()
            .id(doctor.getId())
            .name(doctor.getName())
            .nickname(doctor.getNickname())
            .phone(doctor.getPhone())
            .createdAt(doctor.getCreatedAt())
            .build();
    }



    public static List<DoctorResponseDto> toResponseDtoList(List<Doctor> doctors) {
        return doctors == null ? null
            : doctors.stream().map(DoctorMapper::toResponseDto).toList();
    }
}
