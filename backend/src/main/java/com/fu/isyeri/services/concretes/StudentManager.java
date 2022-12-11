package com.fu.isyeri.services.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fu.isyeri.entities.Student;
import com.fu.isyeri.repository.StudentRepository;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.StudentService;

@Service
public class StudentManager implements StudentService{

	private StudentRepository studentRepository;
	public StudentManager(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Override
	public DataResult<Page<Student>> getAll(Pageable pageable) {
		return new DataResult<Page<Student>>(studentRepository.findAll(pageable), true);
	}

	@Override
	public Result add(Student student) {
		studentRepository.save(student);
		return new Result(true, "Öğrenci eklendi");
	}

	@Override
	public Result delete(int id) {
		studentRepository.deleteById(id);
		return new Result(true, "Öğrenci silindi");
	}

	@Override
	public Student update(int id, Student updateStudent) {
		Student student = studentRepository.findById(id).get();
		student.setStudentCompany(updateStudent.getStudentCompany());
		student.setStudentDepartment(updateStudent.getStudentDepartment());
		student.setStudentFullName(updateStudent.getStudentFullName());
		return studentRepository.save(student);
	}

}
