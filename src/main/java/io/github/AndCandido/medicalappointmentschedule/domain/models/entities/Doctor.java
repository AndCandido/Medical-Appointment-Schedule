package io.github.AndCandido.medicalappointmentschedule.domain.models.entities;

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
@Table(name = "TB_DOCTORS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String nickname;

    private String phone;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
