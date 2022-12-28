package com.fu.isyeri.services.abstracts;

import java.text.ParseException;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fu.isyeri.entities.Student;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;

public interface StudentService {
	
	DataResult<Page<Student>> getAll(Pageable pageable);

	Result add(Student student);
	
	Result delete(int id);

	Student update(int id, Student updateStudent);

	DataResult<Page<Student>> findByDate(Date start, Date end,Pageable pageable) throws ParseException;

}
