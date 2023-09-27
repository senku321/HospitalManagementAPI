package com.Hospital.dto;

import java.util.List;

import com.Hospital.Entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorDto {
	
	private String name;
	private String spec;
	private String pno;
	private List<Patient> patients;
	
}
