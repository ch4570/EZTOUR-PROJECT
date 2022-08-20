package com.devcamp.eztour.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDto {

    private String usr_id;
    private String cmn_cd_prf_img;
    private String cmn_cd_usr_stt;
    private String pwd;
    private String usr_nm;
    private String brth;
    private String gndr;
    private String email;
    private String phn;
    private String rl;
    private String naver_id;
    private String kakao_id;
    private int mlg;
    private Date reg_date;
    private Date lst_acc_date; // 마지막 접속 일자
    private Date rst_chg_date; // 휴면 전환 일자
    private String cmn_cd_drp; // 탈퇴 사유 코드
    private boolean id_check; // 아이디 중복 여부

    public UserDto(){}

    public UserDto(String usr_id, String pwd, String usr_nm, String brth, String gndr, String email, String phn) {
        this.usr_id = usr_id;
        this.pwd = pwd;
        this.usr_nm = usr_nm;
        this.brth = brth;
        this.gndr = gndr;
        this.email = email;
        this.phn = phn;
    }

    public UserDto(String usr_id, String usr_nm, String email, String rl, String phn) {
            this.usr_id = usr_id;
            this.usr_nm = usr_nm;
            this.email = email;
            this.rl = rl;
            this.phn = phn;
    }

}