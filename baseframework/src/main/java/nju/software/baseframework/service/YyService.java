package nju.software.baseframework.service;

import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.dataobject.Yyb;
import nju.software.baseframework.data.vo.TableModel;
import nju.software.baseframework.data.vo.YyChartModel;
import nju.software.baseframework.data.vo.YyxqModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by 28643 on 2018/11/28.
 */
public interface YyService {
    Yyb findByBh(Integer bh);

    TableModel<YyxqModel> SearchYylb(String ah,String ajmc,String lfr,
                                     String jfdd,String lxfs,String lfsj,
                                     Integer yyzt,
                                     Yhb yh, PageRequest pageRequest);

    List<YyxqModel> getYylb(String ah,String ajmc,String lfr,
                            String jfdd,String lxfs,String lfsj,
                            Integer yyzt, Yhb yh);

    YyxqModel getYyxxByBhAndAJXH(Integer bh);

    /**
     * 获得今日数据
     * @param jfr
     * @return
     */
    TableModel<YyxqModel> getTodayData(String jfr,PageRequest pageRequest);

    /**
     * 获得案件相关预约数据
     */
    List<Yyb> getAjYy(String ah,Integer yyzt);

    /**
     * 获得正在发布、结束公告的数据
     * 获得最新预约、历史预约的数据
     * @return
     */
    List<Long> getSjtj();

    /**
     * 获得预约表的审核数据
     * @param pageRequest
     * @return
     */
    Page<YyxqModel> getYySh(PageRequest pageRequest);

    Integer getYybByAh(String ah);
    
    List<YyChartModel> getYyChart(String spt, String ajxz, String jafs, String cbr,
                                  String begin, String last);
}
