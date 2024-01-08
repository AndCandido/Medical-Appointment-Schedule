package io.github.AndCandido.medicalappointmentschedule.domain.services;

import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.DoctorRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Doctor;

import java.util.List;
import java.util.UUID;

public interface IDoctorService {
    Doctor saveDoctor(DoctorRequestDto doctorRequestDto);

    List<Doctor> getAllDoctor();

    Doctor getDoctorById(UUID id);

    Doctor updateDoctorById(UUID id, DoctorRequestDto doctorRequestDto);

    void deleteDoctorById(UUID id);
}
