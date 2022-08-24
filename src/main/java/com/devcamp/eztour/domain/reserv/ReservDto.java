package com.devcamp.eztour.domain.reserv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReservDto {
    //예약정보를 저장하기 위한 Dto
    //예약 list를 불러오기위한 Dto
    private String rsvt_no; //예약번호
    private String prd_dtl_cd; //상세상품번호
    private String usr_id; //유저아이디
    private String prd_cd; //상품코드
    private String mn_rsvt_nm; //대표예약자명
    private String phn; //폰번호
    private String email; //이메일
    private long sum_prc; //합계금액
    private long pay_ftr_prc; //결제예정금액
    private String req_cont; //요청사항
    private String cmn_cd_rsvt_stt = "6A"; //예약상태
    private String cmn_cd_pay_stt = "7E"; //결제 상태
    private Date rsvt_date; //예약일
    private Integer cnc_pay_no; //결제취소번호
    private Integer adt_cnt; //성인수
    private Integer chd_cnt; //아동수
    private Integer bb_cnt; //유아수

    private String prd_nm;
    private String prd_dtl_desc;
    private String img_pth;

    public ReservDto(){}
    public ReservDto(String rsvt_no, String prd_dtl_cd, String usr_id, String prd_cd, String mn_rsvt_nm, String phn, String email, long sum_prc, long pay_ftr_prc, String req_cont, String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, Date rsvt_date, Integer cnc_pay_no, Integer adt_cnt, Integer chd_cnt, Integer bb_cnt) {
        this(rsvt_no, prd_dtl_cd, usr_id, prd_cd, mn_rsvt_nm, phn, email, sum_prc, pay_ftr_prc, req_cont, cmn_cd_rsvt_stt, cmn_cd_pay_stt, rsvt_date, cnc_pay_no, adt_cnt, chd_cnt, bb_cnt, null, null, null);
    }

    public ReservDto(String rsvt_no, String prd_dtl_cd, String usr_id, String prd_cd, String mn_rsvt_nm, String phn, String email, long sum_prc, long pay_ftr_prc, String req_cont, String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, Date rsvt_date, Integer cnc_pay_no, Integer adt_cnt, Integer chd_cnt, Integer bb_cnt, String prd_nm, String prd_dtl_desc, String img_pth) {
        this.rsvt_no = rsvt_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.usr_id = usr_id;
        this.prd_cd = prd_cd;
        this.mn_rsvt_nm = mn_rsvt_nm;
        this.phn = phn;
        this.email = email;
        this.sum_prc = sum_prc;
        this.pay_ftr_prc = pay_ftr_prc;
        this.req_cont = req_cont;
        this.cmn_cd_rsvt_stt = cmn_cd_rsvt_stt;
        this.cmn_cd_pay_stt = cmn_cd_pay_stt;
        this.rsvt_date = rsvt_date;
        this.cnc_pay_no = cnc_pay_no;
        this.adt_cnt = adt_cnt;
        this.chd_cnt = chd_cnt;
        this.bb_cnt = bb_cnt;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
        this.img_pth = img_pth;
    }
}
