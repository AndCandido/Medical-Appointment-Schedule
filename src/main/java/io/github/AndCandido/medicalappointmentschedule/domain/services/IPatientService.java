package io.github.AndCandido.medicalappointmentschedule.domain.services;

import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.PatientRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface IPatientService {
    Patient savePatient(PatientRequestDto patientRequestDto);

    List<Patient> getAllPatients();

    Patient getPatientById(UUID id);

    Patient updatePatientById(UUID id, PatientRequestDto patientRequestDto);

    void deletePatientById(UUID id);
}
