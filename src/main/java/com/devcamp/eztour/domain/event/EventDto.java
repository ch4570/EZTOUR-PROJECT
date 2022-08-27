package com.devcamp.eztour.domain.event;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
public class EventDto {

  private int evnt_no; // 이벤트 번호
  private String evnt_ttl; //이벤트 제목
  private String evnt_cont; // 이벤트 내용
  private Date reg_date; // 등록일
  private Date mdf_date; // 수정일
  private Date fin_date;  // 마감일자
  private Date frs_reg_date; // 최초 등록일자
  private Date frs_rgs_no;  // 최초 등록자 식별번호
  private Date fnl_mod_date;  // 최종 수정일자
  private Date fnl_mod_no;    // 최종 수정자 식별번호
    // 파일이 저장되어 있는 경로

    private String evnt_img_pth; // 이미지
    // 업로드 할 파일명
    private String fileName;
    // 3.  uuid
    private String uuid;
    // 4. 업로드된 파일이 이미지 파일인지 아닌지에 대한 정보
    private boolean image;

    public EventDto(int evnt_no, String evnt_ttl, String evnt_cont, Date reg_date, Date mdf_date, Date fin_date,String evnt_img_pth,String fileName,String uuid,boolean image) {
        this.evnt_no = evnt_no;
        this.evnt_ttl = evnt_ttl;
        this.evnt_cont = evnt_cont;
        this.reg_date = reg_date;
        this.mdf_date = mdf_date;
        this.fin_date = fin_date;
        this.evnt_img_pth = evnt_img_pth;
        this.fileName = fileName;
        this.uuid = uuid;
        this.image = image;
    }

    public EventDto() {

    }

    public EventDto(String finalPath) {
    }


    //    evnt_no,evnt_ttl,evnt_cont,reg_date,mdf_date,fln_date
}
