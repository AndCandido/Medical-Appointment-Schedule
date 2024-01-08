package io.github.AndCandido.medicalappointmentschedule.domain.models.entities;

import io.github.AndCandido.medicalappointmentschedule.domain.enums.AgeGroup;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PATIENTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String contactPhone;

    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
