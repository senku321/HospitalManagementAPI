package com.Hospital.dto;

import com.Hospital.Entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {
	private String name;
	private int age;
	private String gender;
	private String pno;
	private int weight;
	private int height;
	private String bloodgroup;
	private Doctor doctor; 
}
