package com.devcamp.eztour.service.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface RvwService {

    int getCount() throws Exception;

    int remove(Integer rvw_no, String usr_id) throws Exception;

    int write(RvwDto rvwDto) throws Exception;

    List<RvwDto> getList() throws Exception;

    RvwDto read(Integer rvw_no) throws Exception;

    List<RvwDto> getPage(Map map) throws Exception;

    int modify(RvwDto rvwDto) throws Exception;

    int insert(RvwDto rvwDto) throws Exception;

    List<RvwDto> getSearchResultPage(SearchCondition sc) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    RvwDto selectUsernmEmail(String usr_id) throws Exception;

    List<RvwDto> selectPrdnm(String usr_id) throws Exception;
}
