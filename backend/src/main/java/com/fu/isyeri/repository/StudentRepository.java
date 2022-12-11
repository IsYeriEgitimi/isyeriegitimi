package com.fu.isyeri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fu.isyeri.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
