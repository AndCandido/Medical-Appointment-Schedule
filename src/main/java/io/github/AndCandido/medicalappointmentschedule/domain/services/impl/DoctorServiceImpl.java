package io.github.AndCandido.medicalappointmentschedule.domain.services.impl;

import io.github.AndCandido.medicalappointmentschedule.api.exceptions.ResourceNotFound;
import io.github.AndCandido.medicalappointmentschedule.api.utils.ApplicationUtils;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.DoctorRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Doctor;
import io.github.AndCandido.medicalappointmentschedule.domain.models.repositories.AppointmentRepository;
import io.github.AndCandido.medicalappointmentschedule.domain.models.repositories.DoctorRepository;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IDoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record DoctorServiceImpl(
    DoctorRepository doctorRepository,
    AppointmentRepository appointmentRepository

) implements IDoctorService {

    @Override
    public Doctor saveDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorRequestDto, doctor);
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(UUID id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Doctor with id: "+id+" not found"));
    }

    @Override
    public Doctor updateDoctorById(UUID id, DoctorRequestDto doctorRequestDto) {
        Doctor doctorSaved = getDoctorById(id);
        BeanUtils.copyProperties(doctorRequestDto, doctorSaved);
        return doctorRepository.save(doctorSaved);
    }

    @Override
    public void deleteDoctorById(UUID id) {
        Doctor doctor = getDoctorById(id);

        if(doctor.getAppointmentList() != null) {
            for (Appointment appointment : doctor.getAppointmentList()) {
                appointment.setDoctor(null);
                appointmentRepository.save(appointment);
            }
        }

        doctorRepository.delete(doctor);
    }
}
