package com.Hospital.Repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hospital.Entity.Doctor;

@Repository
public interface DocRepository extends JpaRepository<Doctor, Integer>{
	
	Optional<Doctor> findByName(String name);
}
