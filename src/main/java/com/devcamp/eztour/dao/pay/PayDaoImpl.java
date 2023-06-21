package com.devcamp.eztour.dao.pay;

import com.devcamp.eztour.dao.pay.PayDao;
import com.devcamp.eztour.domain.pay.CancelViewDto;
import com.devcamp.eztour.domain.pay.PayDto;
import com.devcamp.eztour.domain.pay.PayResultDto;
import com.devcamp.eztour.domain.pay.PayViewDto;
import com.devcamp.eztour.domain.reserv.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PayDaoImpl implements PayDao {

    @Autowired
    SqlSession session;

    String namespace = "com.devcamp.eztour.payMapper.";


    @Override
    public PayDto selectPay(String rsvt_no) throws Exception {
        return session.selectOne(namespace + "selectPay", rsvt_no);
    }

    @Override
    public String selectPayStatus(Map<String, String> map)throws Exception{
        return session.selectOne(namespace+"selectPayStatus", map);
    }

    @Override
    public int insertPay(PayDto payDto) throws Exception {
        return session.insert(namespace + "insertPay", payDto);
    }

    @Override
    public int deletePayAdmin() throws Exception {
        return session.delete(namespace+"deletePayAdmin");
    }

    @Override
    public CancelViewDto selectCancelInfo(String rsvt_no) throws Exception {
        return session.selectOne(namespace + "selectCancelInfo", rsvt_no);
    }

    @Override
    public PayDto selectPayById(Map<String, String> map) throws Exception{
        return session.selectOne(namespace+"selectPayById", map);
    }

    @Override
    public PayViewDto selectMlgAndPrdInfo(String pay_no) throws Exception {
        return session.selectOne(namespace+"selectMlgAndPrdInfo", pay_no);
    }

    @Override
    public int updatePayAndRsvtResult(PayResultDto payResultDto) throws Exception {
        return session.update(namespace+"updatePayAndRsvtResult", payResultDto);
    }

    @Override
    public List<StatsGndrAndAgePerHourDto> selectGndrAndAgePerHour() throws Exception {
        return session.selectList(namespace+"selectPayGndrAndAgePerHour");
    }

    @Override
    public List<StatsTopListDto> selectTopNList(int limitNum) throws Exception{
        return session.selectList(namespace + "selectTopNPayList", limitNum);
    }

    @Override
    public List<StatsTopListDto> selectTopNPrdLikelyPay(int limitNum) throws Exception{
        return session.selectList(namespace + "selectTopNPrdLikelyPay", limitNum);
    }
}
