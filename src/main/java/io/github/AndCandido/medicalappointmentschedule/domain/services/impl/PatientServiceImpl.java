package io.github.AndCandido.medicalappointmentschedule.domain.services.impl;

import io.github.AndCandido.medicalappointmentschedule.api.exceptions.ResourceNotFound;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.PatientRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;
import io.github.AndCandido.medicalappointmentschedule.domain.models.repositories.AppointmentRepository;
import io.github.AndCandido.medicalappointmentschedule.domain.models.repositories.PatientRepository;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IPatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record PatientServiceImpl(
    PatientRepository patientRepository,
    AppointmentRepository appointmentRepository

) implements IPatientService {

    @Override
    public Patient savePatient(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientRequestDto, patient);
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(UUID id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Patient with id: " + id + " not found"));
    }

    @Override
    public Patient updatePatientById(UUID id, PatientRequestDto patientRequestDto) {
        Patient patientSaved = getPatientById(id);
        BeanUtils.copyProperties(patientRequestDto, patientSaved);
        return patientRepository.save(patientSaved);
    }

    @Override
    public void deletePatientById(UUID id) {
        Patient patient = getPatientById(id);

        if(patient.getAppointmentList() != null) {
            for (Appointment appointment : patient.getAppointmentList()) {
                appointment.setPatient(null);
                appointmentRepository.save(appointment);
            }
        }

        patientRepository.delete(patient);
    }
}
