package example.day06.entity;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public boolean 저장(GoodsDto goodsDto) {
        // 1) DTO > ENTITY 변환
        GoodsEntity goodsEntity = goodsDto.toEntity(); // 1
        // 2) JPA save 이용하여 ENTITY insert 찾기
        GoodsEntity saved = goodsRepository.save(goodsEntity); // 1+2
        // oodsEntity saved=goodsRepository.save(goodsDto.toEntity()); // 2
        // 3) save 결과에 pk여부 성공판단
        if (saved.getGno() >= 1) return true; // 3
        return false;
        // return goodsRepository.save(goodsDto.toEntity()).getGno()>=1?true:false // 1+2+3
    }

    @Transactional // 수정시 여러개 setter 사용함으로
    public boolean 수정(GoodsDto goodsDto){
        // 1) 수정할 ENTITY PK 확인
        int updatePk=goodsDto.getGno();
        // 2) 수정할 ENTITY 찾기 > 영속성
        Optional<GoodsEntity> optional=goodsRepository.findById(updatePk);
        if(optional.isPresent()){ // .isPresent 존재하면
            GoodsEntity updateEntity=optional.get(); // 존재하면 ENTITY 꺼내기
            updateEntity.setGno(goodsDto.getGno());
            updateEntity.setGname(goodsDto.getGname());
            updateEntity.setGprice(goodsDto.getGprice());
            updateEntity.setGdesc(goodsDto.getGdesc());
            return true;
        } return false;
    }
}
