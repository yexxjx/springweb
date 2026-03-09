package example.day07.practice7.service;

import example.day07.practice7.dto.CourseDto;
import example.day07.practice7.entity.CourseEntity;
import example.day07.practice7.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public boolean 과정등록(CourseDto courseDto){
        CourseEntity courseEntity=courseDto.toEntity();
        CourseEntity saved=courseRepository.save(courseEntity);
        if(saved.getCourseId()>=1) return true;
        return false;
    }
}
