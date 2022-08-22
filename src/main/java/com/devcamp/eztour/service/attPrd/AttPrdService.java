package com.devcamp.eztour.service.attPrd;

import com.devcamp.eztour.domain.attPrd.AttPrdDto;

import java.util.List;

public interface AttPrdService {

    int count(String usr_id, String prd_cd) throws Exception;

    int checkAttPrd(AttPrdDto attPrdDto) throws Exception;

    int insert(AttPrdDto attPrdDto) throws Exception;

    int updateLikeUp(AttPrdDto attPrdDto) throws Exception;

    int updateLikeDown(AttPrdDto attPrdDto) throws Exception;

    List<AttPrdDto> selectAll() throws Exception;

    List<AttPrdDto> selectPage(Integer offset, Integer pageSize) throws Exception;

    int deleteAll() throws Exception;

    int deleteUserAll(String usr_id) throws Exception;

    int delete(Integer att_prd_no, String usr_id) throws Exception;
}
