package example.day07.practice7.service;

import example.day07.practice7.dto.EnrollDto;
import example.day07.practice7.entity.EnrollEntity;
import example.day07.practice7.repository.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollService {
    @Autowired
    private EnrollRepository enrollRepository;

    public boolean 수강등록(EnrollDto enrollDto){
        EnrollEntity enrollEntity=enrollDto.toEntity();
        EnrollEntity saved=enrollRepository.save(enrollEntity);
        if(saved.getEnrollId()>=1) return true;
        return false;
    }

    public List<EnrollDto> 수강조회(){
        List<EnrollEntity> enrollEntityList=enrollRepository.findAll();
        List<EnrollDto> enrollDtoList=new ArrayList<>();
        enrollEntityList.forEach((enrollEntity -> {
            EnrollDto enrollDto=new EnrollDto();
            enrollDto.setEnrollId(enrollEntity.getEnrollId());
            enrollDto.setStatus(enrollDto.getStatus());
            enrollDtoList.add(enrollDto);
        }));
        return enrollDtoList;
    }
}
