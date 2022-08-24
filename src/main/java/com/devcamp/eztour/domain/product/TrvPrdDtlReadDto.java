package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TrvPrdDtlReadDto {

    private String prd_cd;
    private String dpr_day;
    private String dstn_cd;
    private String cmn_cd_thm;
    private Integer evnt_yn;
    private Integer fin_yn;
    private Integer act_yn;
    private Integer dc_yn;
    private Integer vcnt;
    private Integer lk_yn;
    private Integer mn_img_yn;
    private String prd_dtl_cd;
    private int	    prd_str_prc;
    private String	arl_nm;
    private Integer	dpr_yn;
    private Integer rsvt_yn;
    private int		pr_rsvt_cnt;
    private int		min_stt_cnt;
    private int		max_stt_cnt;
    private String	loc_dpr_date;
    private String  loc_fin_date;
    private String	frs_reg_date;
    private String	frs_rgs_no;
    private String  fnl_mod_date;
    private String	fnl_mod_no;
    private String prd_nm;
    private String prd_dtl_desc;
    private String trv_per;
    private Integer pc_prc;
    private String img_pth;
    private Date dpr_str_date;
    private Date dpr_fin_date;
    private String nt_cd_nm;
    private String usr_id;
    private String dom_dpr_date;
    private String dom_fin_date;


    public TrvPrdDtlReadDto(String prd_dtl_cd, String prd_cd, String arl_nm, int prd_str_prc, String dom_dpr_date, String prd_nm) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_cd = prd_cd;
        this.prd_str_prc = prd_str_prc;
        this.arl_nm = arl_nm;
        this.dom_dpr_date = dom_dpr_date;
        this.prd_nm = prd_nm;
    }

    public TrvPrdDtlReadDto(String prd_dtl_cd, String prd_cd, int prd_str_prc, String arl_nm, int min_stt_cnt, int max_stt_cnt,
                            String dom_dpr_date, String prd_nm, String dom_fin_date,String loc_dpr_date, String loc_fin_date) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_cd = prd_cd;
        this.prd_str_prc = prd_str_prc;
        this.arl_nm = arl_nm;
        this.min_stt_cnt = min_stt_cnt;
        this.max_stt_cnt = max_stt_cnt;
        this.dom_dpr_date = dom_dpr_date;
        this.prd_nm = prd_nm;
        this.dom_fin_date = dom_fin_date;
        this.loc_dpr_date = loc_dpr_date;
        this.loc_fin_date = loc_fin_date;
        }

    public TrvPrdDtlReadDto(String img_pth,Integer mn_img_yn, String prd_cd, String prd_nm,Integer prd_str_prc) {
        this.img_pth = img_pth;
        this.mn_img_yn = mn_img_yn;
        this.prd_cd = prd_cd;
        this.prd_nm = prd_nm;
        this.prd_str_prc = prd_str_prc;
    }



//    public TrvPrdDtlReadDto(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm, String prd_dtl_desc, String trv_per,
//                            Integer prd_str_prc, Date dpr_str_date, Date dpr_fin_date,
//                            boolean evnt_yn, boolean fin_yn, boolean act_yn, boolean dc_yn,
//                            Integer pc_prc, Integer vcnt, boolean lk_yn, String img_pth,
//                            boolean mn_img_yn, String prd_dtl_cd, String arl_nm, boolean dpr_yn, boolean rsvt_yn,String fin_date) {
//        this.prd_cd = prd_cd;
//        this.dstn_cd = dstn_cd;
//        this.cmn_cd_thm = cmn_cd_thm;
//        this.evnt_yn = evnt_yn;
//        this.fin_yn = fin_yn;
//        this.act_yn = act_yn;
//        this.dc_yn = dc_yn;
//        this.vcnt = vcnt;
//        this.lk_yn = lk_yn;
//        this.mn_img_yn = mn_img_yn;
//        this.prd_dtl_cd = prd_dtl_cd;
//        this.prd_str_prc = prd_str_prc;
//        this.arl_nm = arl_nm;
//        this.dpr_yn = dpr_yn;
//        this.rsvt_yn = rsvt_yn;
//        this.prd_nm = prd_nm;
//        this.prd_dtl_desc = prd_dtl_desc;
//        this.trv_per = trv_per;
//        this.pc_prc = pc_prc;
//        this.img_pth = img_pth;
//        this.dpr_str_date = dpr_str_date;
//        this.dpr_fin_date = dpr_fin_date;
//        this.fin_date = fin_date;
//    }


    // 영욱님 공용 -> 수정 금지
    public TrvPrdDtlReadDto(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm, String prd_dtl_desc, String trv_per,
                            Integer prd_str_prc, Date dpr_str_date, Date dpr_fin_date,
                            Integer evnt_yn, Integer fin_yn, Integer act_yn, Integer dc_yn,
                            Integer pc_prc, Integer vcnt, Integer lk_yn, String img_pth,
                            Integer mn_img_yn) {
        this.prd_cd = prd_cd;
        this.dstn_cd = dstn_cd;
        this.cmn_cd_thm = cmn_cd_thm;
        this.evnt_yn = evnt_yn;
        this.fin_yn = fin_yn;
        this.act_yn = act_yn;
        this.dc_yn = dc_yn;
        this.vcnt = vcnt;
        this.lk_yn = lk_yn;
        this.mn_img_yn = mn_img_yn;
        this.prd_str_prc = prd_str_prc;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
        this.trv_per = trv_per;
        this.pc_prc = pc_prc;
        this.img_pth = img_pth;
        this.dpr_str_date = dpr_str_date;
        this.dpr_fin_date = dpr_fin_date;
    }

    public TrvPrdDtlReadDto(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm, String prd_dtl_desc, String trv_per,
                            Integer prd_str_prc, Date dpr_str_date, Date dpr_fin_date,
                            Integer evnt_yn, Integer fin_yn, Integer act_yn, Integer dc_yn,
                            Integer pc_prc, Integer vcnt, Integer lk_yn, String img_pth,
                            Integer mn_img_yn, String prd_dtl_cd, String arl_nm, Integer dpr_yn, Integer rsvt_yn, String nt_cd_nm) {
        this.prd_cd = prd_cd;
        this.dstn_cd = dstn_cd;
        this.cmn_cd_thm = cmn_cd_thm;
        this.evnt_yn = evnt_yn;
        this.fin_yn = fin_yn;
        this.act_yn = act_yn;
        this.dc_yn = dc_yn;
        this.vcnt = vcnt;
        this.lk_yn = lk_yn;
        this.mn_img_yn = mn_img_yn;
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_str_prc = prd_str_prc;
        this.arl_nm = arl_nm;
        this.dpr_yn = dpr_yn;
        this.rsvt_yn = rsvt_yn;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
        this.trv_per = trv_per;
        this.pc_prc = pc_prc;
        this.img_pth = img_pth;
        this.dpr_str_date = dpr_str_date;
        this.dpr_fin_date = dpr_fin_date;
        this.nt_cd_nm = nt_cd_nm;
    }

    public TrvPrdDtlReadDto(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm, String prd_dtl_desc, String trv_per,
                            Integer prd_str_prc, Date dpr_str_date, Date dpr_fin_date,
                            Integer evnt_yn, Integer fin_yn, Integer act_yn, Integer dc_yn, Integer pc_prc,
                            int vcnt, Integer lk_yn, String img_pth, Integer mn_img_yn,
                            String usr_id,String dpr_day) {
        this.prd_cd = prd_cd;
        this.dstn_cd = dstn_cd;
        this.cmn_cd_thm = cmn_cd_thm;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
        this.trv_per = trv_per;
        this.prd_str_prc = prd_str_prc;
        this.dpr_str_date = dpr_str_date;
        this.dpr_fin_date = dpr_fin_date;
        this.evnt_yn = evnt_yn;
        this.fin_yn = fin_yn;
        this.act_yn = act_yn;
        this.dc_yn = dc_yn;
        this.pc_prc = pc_prc;
        this.vcnt = vcnt;
        this.lk_yn = lk_yn;
        this.img_pth = img_pth;
        this.mn_img_yn = mn_img_yn;
        this.usr_id = usr_id;
        this.dpr_day = dpr_day;
    }

}
