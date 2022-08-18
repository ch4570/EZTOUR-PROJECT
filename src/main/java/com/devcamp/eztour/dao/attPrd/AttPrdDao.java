package com.devcamp.eztour.dao.attPrd;

import com.devcamp.eztour.domain.attPrd.AttPrdDto;

import java.util.List;

public interface AttPrdDao {
    int count() throws Exception;

    int checkAttPrd(AttPrdDto attPrdDto) throws Exception;

    int insert(AttPrdDto attPrdDto) throws Exception;

    int updateLikeUp(AttPrdDto attPrdDto) throws Exception;

    int updateLikeDown(AttPrdDto attPrdDto) throws Exception;

    List<AttPrdDto> selectAll() throws Exception;

    List<AttPrdDto> selectPage() throws Exception;

    int deleteAll() throws Exception;

    int deleteUserAll(String urs_id) throws Exception;

    int delete(Integer att_prd_no, String usr_id) throws Exception;
}
