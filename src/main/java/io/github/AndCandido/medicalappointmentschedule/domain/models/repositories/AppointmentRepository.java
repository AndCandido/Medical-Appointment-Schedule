package io.github.AndCandido.medicalappointmentschedule.domain.models.repositories;

import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}
