package io.github.AndCandido.medicalappointmentschedule.web.controllers;

import io.github.AndCandido.medicalappointmentschedule.domain.mappers.DoctorMapper;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.request.DoctorRequestDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.dtos.response.DoctorResponseDto;
import io.github.AndCandido.medicalappointmentschedule.domain.models.entities.Doctor;
import io.github.AndCandido.medicalappointmentschedule.domain.services.IDoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctors")
public record DoctorController(
    IDoctorService doctorService
) {
    @PostMapping
    public ResponseEntity<DoctorResponseDto> saveDoctor(
        @RequestBody @Valid DoctorRequestDto doctorRequestDto
    ) {
        Doctor doctorSaved = doctorService.saveDoctor(doctorRequestDto);
        DoctorResponseDto doctorResponseDto = DoctorMapper.toResponseDto(doctorSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctor();
        List<DoctorResponseDto> doctorResponseDtoList = DoctorMapper.toResponseDtoList(doctors);
        return ResponseEntity.ok(doctorResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable UUID id) {
        Doctor doctor = doctorService.getDoctorById(id);
        DoctorResponseDto doctorResponseDto = DoctorMapper.toResponseDto(doctor);
        return ResponseEntity.ok(doctorResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctorById(
        @PathVariable UUID id,
        @RequestBody @Valid DoctorRequestDto doctorRequestDto
    ) {
        Doctor doctor = doctorService.updateDoctorById(id, doctorRequestDto);
        DoctorResponseDto doctorResponseDto = DoctorMapper.toResponseDto(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable UUID id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }
}
