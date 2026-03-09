package example.day07.practice7.service;

import example.day07.practice7.dto.StudentDto;
import example.day07.practice7.entity.StudentEntity;
import example.day07.practice7.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean 학생등록(StudentDto studentDto){
        StudentEntity studentEntity=studentDto.toEntity();
        StudentEntity saved=studentRepository.save(studentEntity);
        if(saved.getStudentId()>=1) return true;
        return false;
    }
}
