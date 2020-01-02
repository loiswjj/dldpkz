package nju.software.baseframework.service.impl;

import nju.software.baseframework.convertor.SearchConverter;
import nju.software.baseframework.data.dao.*;
import nju.software.baseframework.data.dataobject.*;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.*;
import nju.software.baseframework.service.AjlbService;
import nju.software.baseframework.service.Enum.GgZTEnum;
import nju.software.baseframework.service.Enum.YYZTEnum;
import nju.software.baseframework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "ajxx")
public class AjlbServiceImpl implements AjlbService {
    @Autowired
    private AJjbDao aJjbDao;

    @Autowired
    private SpryDao spryDao;

    @Autowired
    private DsrDao dsrDao;

    @Autowired
    private YyDao yyDao;

    @Autowired
    private GgDao ggDao;

    @Autowired
    private AJlbDao aJlbDao;

    @Override
    public TableModel<AjlbModel> searchAjlb(String ah, final String ajmc, String dsr, String spz,
                                 String spy, String ajxz, String ajzt, String spt,
                                 String jafs, String sfgd, String sycx, Yhb yh,
                                            PageRequest pageRequest,String fydm) {
        final String sql = SearchConverter.getSearchSql(ah,
                ajmc,dsr,spz,spy,ajxz,ajzt,spt,jafs,sfgd,sycx,yh,1);
        final String sql2 = SearchConverter.getSearchSql(ah,
                ajmc,dsr,spz,spy,ajxz,ajzt,spt,jafs,sfgd,sycx,yh,2);
        long total = aJlbDao.getCount(sql2);
        List<AJjb> aJjbs = aJlbDao.getList(sql,AJjb.class,pageRequest);
        List<AjlbModel> ajlbModels = this.getAjlb(aJjbs,fydm);
        TableModel<AjlbModel> tab = new TableModel<AjlbModel>(total,ajlbModels);
        return tab;
    }

    @Override
    @Cacheable(cacheNames = "ajxxByAh")
    public AjxqModel getAjxxByAh(String ah) {
        AJjb ajjb = aJjbDao.findByAh(ah);
        List<PubSpry> pubSpries = spryDao.findAllByAjxh(ajjb.getAjxh());
        List<DsrJb> dsrJbs = dsrDao.findByAjxh(ajjb.getAjxh());
        String dsr = "";
        for (int i = 0; i < dsrJbs.size(); i++) {
            if (i != 0) {
                dsr += "," + dsrJbs.get(i).getDsrjc();
            } else {
                dsr += dsrJbs.get(i).getDsrjc();
            }
        }
        Map<String, String> sprymap = getSpry(pubSpries);
        AjxqModel ajxqModel = new AjxqModel(ajjb,sprymap,dsr,null);
        return ajxqModel;
    }

    @Override
    public List<GgTjModel> ggTj() {
        return aJlbDao.ggtj();
    }

    @Override
    public List<yyTjModel> yyTj() {
        return aJlbDao.yytj();
    }

    @Override
    @Cacheable(cacheNames = "ajlb")
    public List<AjlbModel> getAllAjsj(String ah, String ajmc, String dsr, String spz,
                                      String spy, String ajxz, String ajzt, String spt,
                                      String jafs, String sfgd, String sycx, Yhb yh,String fydm) {
        final String sql = SearchConverter.getSearchSql(ah,
                ajmc,dsr,spz,spy,ajxz,ajzt,spt,jafs,sfgd,sycx,yh,1);
        List<AJjb> aJjbs = aJlbDao.getList(sql,AJjb.class,null);
        return this.getAjlb(aJjbs,fydm);
    }

    @Override
    @Cacheable(cacheNames = "spry")
    public List<PubSpry> findAllByAjxh(Integer ajxh){
        return spryDao.findAllByAjxh(ajxh);
    }

    private List<AjlbModel> getAjlb(List<AJjb> aJjbs,String fydm){
        List<List<PubSpry>> lists = new ArrayList<>();
        for (int i = 0; i <aJjbs.size() ; i++) {
            Integer ajxh = aJjbs.get(i).getAjxh();
            List<PubSpry> spries = findAllByAjxh(ajxh);
            lists.add(spries);
        }
        /**
         * 切换至本地库
         */
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        List<AjlbModel> ajlbModels = new ArrayList<>();
        for (int i = 0; i < aJjbs.size() ; i++) {
            //查询预约表
            AJjb aJjb = aJjbs.get(i);
            List<PubSpry> spries = lists.get(i);
            List<Yyb> yyb = yyDao.findByAh(aJjb.getAh());
            List<Ggb> ggbList = ggDao.findByAh(aJjb.getAh());

            String yyzt = "未预约";
            String cpggzt = "未发布";
            String pqggzt = "未发布";
            String zzgg = "无";
            String sxgg = "无";
            String cdsggzt = "未发布";
            String zdyggzt = "未发布";
            String sdpjzt = "未发布";

            if(aJjb.getAjxz().trim().equals("8")){
                zzgg = "未发布";
                sxgg = "未发布";
            }

            if(yyb!=null&&yyb.size()!=0){
                yyzt = YYZTEnum.getMsgByStatus(yyb.get(0).getYyzt());
            }
            if (ggbList!=null&&ggbList.size()!=0){
                for (int j = 0; j < ggbList.size(); j++) {
                    Ggb ggb = ggbList.get(j);
                    Integer gglx = ggb.getGglx();
                    switch (gglx){
                        case 1:
                            cpggzt = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                        case 2:
                            pqggzt = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                        case 3:
                            sdpjzt = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                        case 4:
                            zzgg = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                        case 5:
                            sxgg = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                        case 6:
                            cdsggzt = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                        case 7:
                            zdyggzt = GgZTEnum.getExplainByNumber(
                                    Integer.toString(ggb.getStatus()));
                            break;
                    }
                }
            }
            AjlbModel ajlbModel = new AjlbModel(aJjb,getSpry(spries),
                    yyzt,cpggzt,pqggzt,zzgg,sxgg,cdsggzt,zdyggzt,sdpjzt);
            ajlbModels.add(ajlbModel);
        }
        return ajlbModels;
    }

    private Map<String, String> getSpry(List<PubSpry> spries) {
        Map<String, String> sprymap = new HashMap<>();
        sprymap.put("sjy", null);
        sprymap.put("fgzl", null);
        sprymap.put("spz", null);
        sprymap.put("spy", null);

        for (int i = 0; i < spries.size(); i++) {
            PubSpry spry = spries.get(i);
            if (spry.getFg() != null) {
                if (spry.getFg().equals("0")) {
                    //书记员
                    sprymap.put("sjy", spry.getXm());
                } else if (spry.getFg().equals("3")) {
                    //法官助理
                    sprymap.put("fgzl", spry.getXm());
                }
            }
            if (spry.getSfspz() != null && spry.getSfspz().equals("Y")) {
                sprymap.put("spz", spry.getXm());
            }
            if (spry.getSfspz() != null && spry.getSfspz().equals("Y")) {
                sprymap.put("spy", spry.getXm());
            }
        }
        return sprymap;
    }
}