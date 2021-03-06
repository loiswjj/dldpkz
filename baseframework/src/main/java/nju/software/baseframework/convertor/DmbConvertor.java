package nju.software.baseframework.convertor;

import nju.software.baseframework.data.dao.DmbDao;
import nju.software.baseframework.data.dao.PubXtgl;
import nju.software.baseframework.data.dataobject.PubDmb;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.FYEnum;
import nju.software.baseframework.data.vo.LBBHEnum;
import nju.software.baseframework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于获得相应对象的dmb信息
 */
@Service
@CacheConfig(cacheNames = "Initial")
public class DmbConvertor {
    @Autowired
    DmbDao dmbDao;

    @Autowired
    PubXtgl pubXtgl;

    /**
     * ajxz sycx spt
     * @param lbmc
     * @return
     */
    @Cacheable(cacheNames = "default")
    public List<PubDmb> getDmInfo(String lbmc){
        String lbbh = LBBHEnum.getLbbhbyLbmc(lbmc);
        return dmbDao.findAllByLbbh(lbbh);
    }

    @Cacheable(cacheNames = "lbbh")
    public Map<String,List<PubDmb>> getAllDmInfo(String lbmc){
        String[] _list = LBBHEnum.getLbbhbyLbmc(lbmc).split(",");
        Map<String,List<PubDmb>> map = new HashMap<>();
        for (int i = 0; i <_list.length ; i++) {
            String ajxz = _list[i].substring(0,1);
            String str = _list[i].substring(2,_list[i].length());
            map.put(ajxz,dmbDao.findAllByLbbh(str));
        }
        return map;
    }

    private List<PubDmb> getLbInfo(String lbmc, String ajxz, String spcx) {
        String lbbh = LBBHEnum.getLbbhbyLbmc(lbmc);
        String[] lists = lbbh.split(",");
        for (int i = 0; i < lists.length; i++) {
            String str = lists[i];
            String item1 = str.substring(0, 1);
            String item2 = str.substring(1, 2);
            if (ajxz.equals(item1) && spcx.equals(item2)) {
                lbbh = str.substring(2, str.length());
                break;
            }
        }
        for (int i = 0; i < lists.length; i++) {
            String str = lists[i];
            String item1 = str.substring(0, 1);
            String item2 = str.substring(1, 2);
            if (ajxz.equals(item1) && item2.equals("*")) {
                lbbh = str.substring(2, str.length());
                break;
            }
        }
        return dmbDao.findAllByLbbh(lbbh);
    }

    public Map<String, List<String>> getDsr(String lbmc, String ajxz, String spcx) {
        Map<String, List<String>> dsr = new HashMap<>();
        ajxz = StringUtil.trim(ajxz);
        if(ajxz.equals("3")) ajxz="2";
        spcx = StringUtil.trim(spcx);
        List<PubDmb> dmbs = getLbInfo(lbmc, ajxz, spcx);
        List<String> yg = new ArrayList<>();
        List<String> bg = new ArrayList<>();
        for (int i = 0; i <dmbs.size() ; i++) {
            PubDmb dmb = dmbs.get(i);
            String bz = dmb.getBz();
            if(bz!=null&&bz.equals("0")){
                //原告
                yg.add(dmb.getDmbh());
            }
            if(bz!=null&&bz.equals("1")){
                //被告
                bg.add(dmb.getDmbh());
            }
        }
        dsr.put("ygbh",yg);
        dsr.put("bgbh",bg);
        return dsr;
    }

    /**
     * 每家法院的办案审判庭都不一样
     */
    public Map<String,List<PubDmb>> getSpt(){
        Map<String,List<PubDmb>> map = new HashMap<>();
        for (FYEnum fy: FYEnum.values()){
            DynamicDataSource.router(fy.getFydm());
            String sjxx = pubXtgl.findBySzbAndSzl("PUB_AJ_JB","BASPT");
            List<PubDmb> _list = dmbDao.findAllByLbbh(sjxx);
            map.put(fy.getFydm(),_list);
        }
        return map;
    }
}
