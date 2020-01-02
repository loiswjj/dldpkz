package nju.software.baseframework.service;

import nju.software.baseframework.data.dataobject.PubSpry;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.vo.*;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AjlbService {
    TableModel<AjlbModel> searchAjlb(String ah, String ajmc, String dsr, String spz,
                                     String spy, String ajxz, String ajzt, String spt,
                                     String jafs, String sfgd, String sycx,
                                     Yhb yh, PageRequest pageRequest,String fydm);

    AjxqModel getAjxxByAh(String ah);

    List<GgTjModel> ggTj();

    List<yyTjModel> yyTj();

    List<AjlbModel> getAllAjsj(String ah, String ajmc, String dsr, String spz,
                               String spy, String ajxz, String ajzt, String spt,
                               String jafs, String sfgd, String sycx,
                               Yhb yh,String fydm);
    List<PubSpry> findAllByAjxh(Integer ajxh);
}
