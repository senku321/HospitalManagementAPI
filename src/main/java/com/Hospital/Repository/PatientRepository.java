package com.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Hospital.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	Patient findByName(String name);

}
