package nju.software.baseframework.service.impl;

import nju.software.baseframework.Timer.AppInitializer;
import nju.software.baseframework.convertor.DmbConvertor;
import nju.software.baseframework.data.dao.DmbDao;
import nju.software.baseframework.data.dao.NavDao;
import nju.software.baseframework.data.dao.PubXtgl;
import nju.software.baseframework.data.dao.YhbDao;
import nju.software.baseframework.data.dataobject.Nav;
import nju.software.baseframework.data.dataobject.PubDmb;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.FYEnum;
import nju.software.baseframework.data.vo.TableModel;
import nju.software.baseframework.service.YhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "yhb")
public class YhServiceImpl implements YhService {

    @Autowired
    YhbDao yhbDao;

    @Autowired
    NavDao navDao;

    @Autowired
    PubXtgl pubXtgl;

    @Resource
    DmbConvertor dmbConvertor ;

    @Override
    public String checkYhPwd(Yhb yh,String yhkl) {
        if(yh==null) return "该用户不存在!";
        if(yh.getYhkl().equals(yhkl)) return "success";
        return "用户名密码错误!";
    }

    @Override
    @Cacheable(cacheNames = "yhcx")
    public Yhb getYh(String yhmc) {
        return yhbDao.findByYhdm(yhmc);
    }

    @Override
    @Cacheable(cacheNames = "yhxx")
    public Yhb getYhByYhbh(Integer yhbh) {
        return yhbDao.findByYhbh(yhbh);
    }

    @Override
    public Map<Integer,List<Nav>> getNav(Integer level) {
        Map<Integer,List<Nav>>  res = new HashMap<>();
        List<Nav> _list = navDao.findAllByLevel(level);
        for (Nav nav : _list){
            if(nav.getFdh()==0){
                List<Nav> navs = res.get(nav.getId());
                if (navs==null){
                    List<Nav> item = new ArrayList<>();
                    item.add(nav);
                    res.put(nav.getId(),item);
                }else {
                    navs.add(nav);
                    res.put(nav.getId(),navs);
                }
            }else {
                List<Nav> navs = res.get(nav.getFdh());
                if (navs==null){
                    List<Nav> item = new ArrayList<>();
                    item.add(nav);
                    res.put(nav.getFdh(),item);
                }else {
                    navs.add(nav);
                    res.put(nav.getFdh(),navs);
                }
            }
        }
        return res;
    }

    @Override
    @Cacheable(cacheNames = "xxxMs")
    public String getMs(String lbmc,String bh) {
        List<PubDmb> list = dmbConvertor.getDmInfo(lbmc);
        for (int i = 0; i < list.size(); i++) {
            PubDmb dmb = list.get(i);
            if(dmb.getDmbh().equals(bh)){
                return dmb.getDmms();
            }
        }
        return null;
    }

    private String getYhdm(List<PubDmb> _list,String bh){
        for (int i = 0; i < _list.size(); i++) {
            PubDmb pubDmb = _list.get(i);
            if(pubDmb.getDmbh().equals(bh)){
                return pubDmb.getDmms();
            }
        }
        return null;
    }

    @Override
    public TableModel<Yhb> getAllYh(String fydm,PageRequest pageRequest) {
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Page<Yhb> yhbs = yhbDao.findAll(pageRequest);
        List<PubDmb> dmbs = AppInitializer.spt.get(fydm);
        List<Yhb> list = new ArrayList<>();
        for (int i = 0; i <yhbs.getContent().size() ; i++) {
            Yhb yh = yhbs.getContent().get(i);
            String bm = getYhdm(dmbs,yh.getYhbm());
            yh.setYhbm(bm);
            list.add(yh);
        }
        TableModel<Yhb> tab = new TableModel<>(yhbs.getTotalElements(),list);
        return tab;
    }

    @Override
    public void UpdateYhxx(Yhb yhb) {
        yhbDao.save(yhb);
    }

    @Override
    public Integer getYhqx(String yhdm) {
        return yhbDao.getYhxq(yhdm);
    }
}
