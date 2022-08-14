package com.devcamp.eztour.service.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface RvwService {

    int getCount() throws Exception;

    int remove(Integer rvw_no, String rvw_nm) throws Exception;

    int write(RvwDto rvwDto) throws Exception;

    List<RvwDto> getList() throws Exception;

    RvwDto read(Integer rvw_no) throws Exception;

    List<RvwDto> getPage(Map map) throws Exception;

    int modify(RvwDto rvwDto) throws Exception;

    int insert(RvwDto rvwDto) throws Exception;

    RvwDto selectUserEmail(String usr_id) throws Exception;

    List<RvwDto> selectUsernmEmailPrdnm(String usr_id) throws Exception;
}
