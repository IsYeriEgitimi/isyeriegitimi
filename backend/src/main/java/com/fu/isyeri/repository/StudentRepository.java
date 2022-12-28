package com.fu.isyeri.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fu.isyeri.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	Page<Student> findByStudentDateBetween(Date start, Date end, Pageable pageable);
	
}
