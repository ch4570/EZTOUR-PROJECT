package com.devcamp.eztour.domain.user;

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
    private int mlg;
    private Date reg_date;
    private Date lst_acc_date; // 마지막 접속 일자
    private Date rst_chg_date; // 휴면 전환 일자

    public UserDto(String usr_id, String cmn_cd_prf_img, String cmn_cd_usr_stt, String pwd, String usr_nm) {
        this.usr_id = usr_id;
        this.cmn_cd_prf_img = cmn_cd_prf_img;
        this.cmn_cd_usr_stt = cmn_cd_usr_stt;
        this.pwd = pwd;
        this.usr_nm = usr_nm;
    }
}