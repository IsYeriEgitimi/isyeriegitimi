package com.fu.isyeri.controllers;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.entities.Student;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.StudentService;

@RestController
@RequestMapping("/api/1.0/student")
public class StudentController {
	
private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/getAll")
	public DataResult<Page<Student>> getAll(Pageable pageable){
		return studentService.getAll(pageable);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody Student student) {
		return studentService.add(student);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return studentService.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public DataResult<Student> update(@Valid @RequestBody Student updateStudent, @PathVariable int id) {
		Student student = studentService.update(id, updateStudent);
		return new DataResult<Student>(student, true, "Öğrenci güncellendi");
	}

}
