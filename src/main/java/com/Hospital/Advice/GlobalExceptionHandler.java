package com.Hospital.Advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Hospital.Exception.DoctorNotFoundException;
import com.Hospital.Exception.PatientNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String,String> errormsg=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errormsg.put(error.getField(), error.getDefaultMessage());
		});
	return errormsg;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(PatientNotFoundException.class)
	public Map<String,String> handlePatientException(PatientNotFoundException ex){
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("errormessage", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DoctorNotFoundException.class)
	public Map<String,String> handleDoctorException(DoctorNotFoundException ex){
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("errormessage", ex.getMessage());
		return errorMap;
	}
	
}
