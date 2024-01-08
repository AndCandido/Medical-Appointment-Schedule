package io.github.AndCandido.medicalappointmentschedule.domain.models.repositories;

import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
