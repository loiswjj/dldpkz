package nju.software.baseframework.service;

import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.vo.GgModel;
import nju.software.baseframework.data.vo.GgxqModel;
import nju.software.baseframework.data.vo.TableModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by 28643 on 2018/11/30.
 */
public interface GgService {
    TableModel<GgxqModel> getTodayGG(String fbr, PageRequest pageRequest);

    GgxqModel findGgbByBh(Integer id);

    GgxqModel findGgbByAhAndGglx(String ah,Integer gglx);

    GgModel getGgModelByAh(String ah);

    TableModel<GgxqModel> searchGglb(String ah, String ajmc, String fbsj,
                                     Integer gglx, Integer ggzt, Yhb yh, PageRequest pageRequest);

    List<GgxqModel> searchGg(String ah,String ajmc,String fbsj,Integer gglx,
                             Integer ggzt,Yhb yh);

    /**
     * @param ah
     * @return
     */
    Map<String,List<String>> getAjDsr(String ah);

    /**
     * 获得跟案件相关的公告数据
     * @param ah
     * @param ggzt
     * @return
     */
    List<GgxqModel> getAjgg(String ah,Integer ggzt);

    /**
     * 获得公告表的审核数据
     * @param pageRequest
     * @return
     */
    Page<GgxqModel> getGgSh(PageRequest pageRequest,Yhb yh);

    /**
     * 用于获取信访库里面的公告
     * @return
     */
    Map<Integer,List<Ggb>> getGgnrList();

    /**
     * 获得所有公告
     * @param pageRequest
     * @return
     */
    Page<Ggb> getGgControllist(PageRequest pageRequest);
}
