package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.dao.customercenter.CustomerInquiryDao;
import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerCenterServiceImpl implements CustomerCenterService {
    @Autowired
    CustomerInquiryDao customerInquiryDao;

    @Override
    public int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
       return customerInquiryDao.insertCustomerInquiry(customerInquiryDto);
    }

    @Override
    public int getCountCustomerInquiry() throws Exception{
        return customerInquiryDao.countCustomerInquiry();
    }

    @Override
    public int removeCustomerInquiry(Integer bno, String writer) throws Exception {
        return 0;
    }

// 1:1문의 삭제
//    @Override
//    public int removeCustomerInquiry(Integer bno, String writer) throws Exception{
//        return customerInquiryDao.deleteCustomerInquiry(qna_no, usr_id);
//    }

    @Override
    public List<CustomerInquiryDto> getListCustomerInquiry() throws Exception{
        return customerInquiryDao.selectAllCustomerInquiry();
    }

    @Override
    public CustomerInquiryDto readCustomerInquiry(Integer bno) throws Exception {
        return null;
    }

//    조회수 카운트
//    @Override
//    public CustomerInquiryDto readCustomerInquiry(Integer bno) throws Exception{
//        CustomerInquiryDto customerInquiryDto = customerInquiryDao.selectCustomerInquiry(qna_no);
//        customerInquiryDao.increaseView(qna_no);
//        return customerInquiryDto;
//    }

    @Override
    public List<CustomerInquiryDto> getPageCustomerInquiry(Map map) throws Exception{
        return customerInquiryDao.selectCustomerPage(map);
    }

    @Override
    public int modifyCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception{
        return customerInquiryDao.updateCustomerInquiry(customerInquiryDto);
    }


    @Override
    public List<CustomerInquiryDto> getCustomerSearchResultPage(CustomerSearchCondition csc) throws Exception{
        return customerInquiryDao.searchSelectCustomerPage(csc);
    }

    @Override
    public int getCustomerSearchResultCnt(CustomerSearchCondition csc) throws Exception{
        return customerInquiryDao.searchResultCustomerCnt(csc);
    }
}
