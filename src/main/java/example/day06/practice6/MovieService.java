package example.day06.practice6;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;


    public List<MovieDto> 조회(){
        List<MovieEntity> movieEntityList=movieRepository.findAll();
        List<MovieDto> movieDtoList=new ArrayList<>();
        movieEntityList.forEach(movieEntity -> {
            MovieDto movieDto=new MovieDto();
            movieDto.setMid(movieEntity.getMid());
            movieDto.setMtitle(movieEntity.getMtitle());
            movieDto.setMdirector(movieEntity.getMdirector());
            movieDto.setMrelease(movieEntity.getMrelease());
            movieDto.setMrating(movieEntity.getMrating());
            movieDtoList.add(movieDto);
        });
        return movieDtoList;
    }

    public boolean 등록(MovieDto movieDto){
        MovieEntity movieEntity=movieDto.toEntity();
        MovieEntity saved=movieRepository.save(movieEntity);
        if(saved.getMid()>=1) return true;
        return false;
    }

    @Transactional
    public boolean 수정(MovieDto movieDto){
        int updatePk=movieDto.getMid();
        Optional<MovieEntity> optional=movieRepository.findById(updatePk);
        if(optional.isPresent()){
            MovieEntity updateEntity=optional.get();
            updateEntity.setMid(movieDto.getMid());
            updateEntity.setMtitle(movieDto.getMtitle());
            updateEntity.setMdirector(movieDto.getMdirector());
            updateEntity.setMrelease(movieDto.getMrelease());
            updateEntity.setMrating(movieDto.getMrating());
            return true;
        } return false;
    }

    public boolean 삭제(int mid){
        movieRepository.deleteById(mid);
        return true;
    }
}
