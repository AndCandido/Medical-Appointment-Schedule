package io.github.AndCandido.medicalappointmentschedule.domain.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "TB_APPOINTMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
