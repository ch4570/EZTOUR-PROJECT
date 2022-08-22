package com.devcamp.eztour.dao.product;


import com.devcamp.eztour.domain.product.TrvPrdWriteDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;

    // 상품 등록 테스트
    @Test
    public void insertTest() throws Exception{
        deleteAll();
        int result = 0;
        int cnt = 0;
      for(int i=0; i<10; i++){
//          TrvPrdWriteDto trvPrdWriteDto = new  TrvPrdWriteDto("france"+i, "fr"+i, "notheme"+i, "프랑스로 여행",
//                  "프랑스는 베르사유 궁전이 유명", "10박 12일", 300000,
//                  "2022-10-21", "2022-10-31");
//          result = productDao.insertProduct(trvPrdWriteDto);
          if(result ==1){
              cnt++;
          }
      }

      assertTrue(cnt==10);
    }

    // 상품 전부 선택 테스트
    @Test
    public void selectTest() throws Exception{
        insertTest();
        List<TrvPrdWriteDto> list = productDao.selectAllProduct();
        assertTrue(list.size()==10);

    }

    // 상품 업데이트 테스트
    @Test
    public void updateTest() throws Exception{
        insertTest();
        List<TrvPrdWriteDto> list = productDao.selectAllProduct();
        TrvPrdWriteDto trvPrdWriteDto = list.get(0);
        int result = productDao.updateProduct(trvPrdWriteDto);
        assertTrue(result==1);
    }

    // 상품 전체 삭제 테스트
    @Test
    public void deleteAll() throws Exception{
        List<TrvPrdWriteDto> list = productDao.selectAllProduct();
        int count = list.size();
        int result = productDao.deleteAll();
        assertTrue(count==result);
    }

}