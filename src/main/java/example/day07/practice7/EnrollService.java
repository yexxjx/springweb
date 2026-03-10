package example.day07.practice7;

import example.day07.practice7.entity.CourseEntity;
import example.day07.practice7.entity.EnrollEntity;
import example.day07.practice7.entity.StudentEntity;
import example.day07.practice7.repository.CourseRepository;
import example.day07.practice7.repository.EnrollRepository;
import example.day07.practice7.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EnrollService {

    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnrollRepository enrollRepository;

    public boolean 과정등록(Map<String, Object> map){
        CourseEntity saveEntity=new CourseEntity();
        saveEntity.setCourseName(String.valueOf(map.get("courseName")));
        CourseEntity saved=courseRepository.save(saveEntity);
        if(saved.getCourseId()>=1){return true;}
        else{return false;}
    }

    public boolean 학생등록(Map<String, Object> map){
        StudentEntity saveEntity=new StudentEntity();
        saveEntity.setStudentName(String.valueOf(map.get("studentName")));
        StudentEntity saved=studentRepository.save(saveEntity);
        if(saved.getStudentId()>=1) {return true;}
        else{return false;}
    }

    public boolean 수강등록(Map<String, Object> map){
        // 1) 수강신청 엔티티 생성
        EnrollEntity savedEntity=new EnrollEntity();
        savedEntity.setStatus(String.valueOf(map.get("status")));
            // *** FK에 해당하는 엔티티 대입 ***
            // [1] FK 확인, DTO/MAP 등
            int courseId=Integer.parseInt(String.valueOf(map.get("courseId")));
            // [2] FK에 해당하는 엔티티 찾기
            Optional<CourseEntity> optionalCourse = courseRepository.findById(courseId);
            if(optionalCourse.isPresent()){ // [3] 만약에 해당 엔티티가 존재하면
                CourseEntity courseEntity=optionalCourse.get(); // [4] 엔티티 꺼내기
                savedEntity.setCourseEntity(courseEntity); // [5] FK 엔티티를 단방향에 저장
            } else{return false;}

            // *** FK에 해당하는 엔티티 대입 ***
            int studentId=Integer.parseInt(String.valueOf(map.get("studentId")));// 학생 번호(StudentId) 확인
            Optional<StudentEntity> optionalStudent=studentRepository.findById(studentId); // 학생 번호(StudentId)에 해당하는 엔티티 1개 조회
            if(optionalStudent.isPresent()){ // 조회한 결과가 존재하면
                StudentEntity studentEntity=optionalStudent.get(); // 엔티티 꺼내기
                savedEntity.setStudentEntity(studentEntity); // 학생 엔티티를 수강신청엔티티에 대입
            } else{return false;}

        // 2) 수강신청 엔티티 저장
        EnrollEntity saved=enrollRepository.save(savedEntity);
        if(saved.getEnrollId()>=1){return true;}
        else{return false;}
    }

    public Map<String, Object> 개별조회(int enrollId){
        Optional<EnrollEntity> optionalEnroll=enrollRepository.findById(enrollId);
        if(optionalEnroll.isPresent()){
            EnrollEntity enrollEntity=optionalEnroll.get();
            Map<String, Object> map=new HashMap<>();
            map.put("enrollId", enrollEntity.getEnrollId());
            map.put("status", enrollEntity.getStatus());
            map.put("createDate", enrollEntity.getCreateDate());
            map.put("updateDate", enrollEntity.getUpdateDate());
            map.put("courseName", enrollEntity.getCourseEntity().getCourseName()); // FK 엔티티내 특정한 필드만
            map.put("studentName", enrollEntity.getStudentEntity().getStudentName()); // FK 엔티티내 특정한 필드만
            return map;
        } return null;
    }
}
