package nju.software.baseframework.service.impl;

import nju.software.baseframework.convertor.DmbConvertor;
import nju.software.baseframework.convertor.SearchConverter;
import nju.software.baseframework.data.dao.*;
import nju.software.baseframework.data.dataobject.*;
import nju.software.baseframework.data.vo.GgModel;
import nju.software.baseframework.data.vo.GgxqModel;
import nju.software.baseframework.data.vo.TableModel;
import nju.software.baseframework.service.GgService;
import nju.software.baseframework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by 28643 on 2018/11/30.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "ggb")
public class GgServiceImpl implements GgService {
    @Resource
    AJjbDao aJjbDao;
    @Resource
    GgDao ggDao;

    @Autowired
    DsrDao dsrDao;

    @Autowired
    DsrDwDao dsrDwDao;

    @Autowired
    LaAyDao laAyDao;

    @Resource
    DmbConvertor dmbConvertor;

    @Autowired
    AJlbDao aJlbDao;

    @Override
    public TableModel<GgxqModel> getTodayGG(String fbr,PageRequest pageRequest) {
        //如果登陆的是管理员则显示所有的今日公告
        String fbsj = DateUtil.format(new Date(),DateUtil.shortFormat);
        String hql1 = "from Ggb where fbr='"+fbr+"' and fbsj='"+fbsj+"'";
        String hql2 = "select count(ggb) from Ggb ggb where ggb.fbr='"
                +fbr+"' and ggb.fbsj='"+fbsj+"'";
        if(fbr=="系统管理员"){
            hql1 = "from Ggb where fbsj='"+fbsj+"'";
            hql2 = "select count(ggb) from Ggb ggb where ggb.fbsj='"+fbsj+"'";
        }
        List<Ggb> _list = aJlbDao.getList(hql1,Ggb.class,pageRequest);
        List<GgxqModel> ggbs = new ArrayList<>();
        for (int i = 0; i <_list.size() ; i++) {
            ggbs.add(new GgxqModel(_list.get(i)));
        }
        long count = aJlbDao.getCount(hql2);
        TableModel<GgxqModel> tableModel = new TableModel<>(count,ggbs);
        return tableModel;
    }

    @Override
    public GgxqModel findGgbByBh(Integer id) {
        Ggb ggb = ggDao.findBybh(id);
        GgxqModel ggxqModel = new GgxqModel(ggb);
        return ggxqModel;
    }

    @Override
    public GgxqModel findGgbByAhAndGglx(String ah, Integer gglx) {
        Ggb ggb = ggDao.findBybh(ggDao.findByAhAndGglx(ah,gglx));
        GgxqModel ggxqModel = new GgxqModel(ggb);
        return ggxqModel;
    }

    @Override
    public GgModel getGgModelByAh(String ah) {
        AJjb aJjb = aJjbDao.findByAh(ah);
        Map<String,List<String>> map = dmbConvertor.getDsr("ssdw",
                aJjb.getAjxz(),aJjb.getSpcx());
        Map<String,List<String>> dsr = findYG(map,dsrDao.findByAjxh(aJjb.getAjxh()));
        List<String> yg = dsr.get("yg");
        List<String> bg = dsr.get("bg");

        String bgbd = "";
        DsrDw dsrDw = null;
        if(bg.size()!=0){
            dsrDw = dsrDwDao.getByAjxhAndDwmc(aJjb.getAjxh(),bg.get(0));
        }
        if(dsrDw!=null){
            bgbd = dsrDw.getFddbrxm();
        }
        String ay = laAyDao.getByAjxh(aJjb.getAjxh()).getLaay();
        GgModel ggModel = new GgModel(yg,bg,aJjb.getAh(),ay,bgbd,aJjb.getAjmc(),
                aJjb.getAjxh());
        return ggModel;
    }

    private Map<String,List<String>> findYG(Map<String,List<String>> map,
                                            List<DsrJb> dsrJbs){
        List<String> yg = map.get("ygbh");
        List<String> bg = map.get("bgbh");
        List<String> dsr_yg = new ArrayList<>();
        List<String> dsr_bg = new ArrayList<>();
        Map<String,List<String>> dsr = new HashMap<>();
        for (int i = 0; i <dsrJbs.size() ; i++) {
            for (int j = 0; j <yg.size() ; j++) {
                if(yg.get(j).equals(dsrJbs.get(i).getDsrssdw())){
                    dsr_yg.add(dsrJbs.get(i).getDsrjc());
                }
            }
            for (int j = 0; j <bg.size() ; j++) {
                if(bg.get(j).equals(dsrJbs.get(i).getDsrssdw())){
                    dsr_bg.add(dsrJbs.get(i).getDsrjc());
                }
            }
        }
        dsr.put("yg",dsr_yg);
        dsr.put("bg",dsr_bg);
        return dsr;
    }

    @Override
    public TableModel<GgxqModel> searchGglb(String ah, String ajmc, String fbsj,
                                            Integer gglx, Integer ggzt, Yhb yh,
                                            PageRequest pageRequest) {
        List<GgxqModel> ggbList = new ArrayList<>();
        String sql = SearchConverter.getGgSql(ah,ajmc,fbsj,gglx,ggzt,yh,0);
        String sql2 = SearchConverter.getGgSql(ah,ajmc,fbsj,gglx,ggzt,yh,1);
        long total = aJlbDao.getCount(sql2);
        List<Ggb> list = aJlbDao.getList(sql,Ggb.class,pageRequest);
        for (int i = 0; i < list.size(); i++) {
            GgxqModel model = new GgxqModel(list.get(i));
            ggbList.add(model);
        }
        TableModel<GgxqModel> table = new TableModel<>(total,ggbList);
        return table;
    }

    @Override
    public List<GgxqModel> searchGg(String ah, String ajmc, String fbsj, Integer gglx, Integer ggzt, Yhb yh) {
        String sql = SearchConverter.getGgSql(ah,ajmc,fbsj,gglx,ggzt,yh,0);
        List<Ggb> ggbList = aJlbDao.getList(sql,Ggb.class,null);
        List<GgxqModel> models = new ArrayList<>();
        for (int i = 0; i < ggbList.size(); i++) {
            GgxqModel model = new GgxqModel(ggbList.get(i));
            models.add(model);
        }
        return models;
    }

    @Override
    @Cacheable(cacheNames = "AjDsr")
    public Map<String,List<String>> getAjDsr(String ah) {
        AJjb aJjb = aJjbDao.findByAh(ah);
        Map<String,List<String>> map = dmbConvertor.getDsr("ssdw",
                aJjb.getAjxz(),aJjb.getSpcx());
        Map<String,List<String>> dsr = findYG(map,dsrDao.findByAjxh(aJjb.getAjxh()));
        return dsr;
    }

    @Override
    public List<GgxqModel> getAjgg(String ah, Integer ggzt) {
        List<GgxqModel> models = new ArrayList<>();
        List<Ggb> list;
        if(ggzt==null||ggzt==5){
            list = ggDao.findAllAjgg(ah);
        }else {
            list = ggDao.findAjgg(ah,ggzt);
        }
        for (int i = 0; i <list.size() ; i++) {
            models.add(new GgxqModel(list.get(i)));
        }
        return models;
    }

    @Override
    public Page<GgxqModel> getGgSh(PageRequest pageRequest,Yhb yh) {
        Page<Ggb> _list = null;
        //两种用户需要审核 1.管理员 2.各庭庭长
        if(yh.getYhbh()!=1){
            //用户身份为各庭庭长
            String sql = "select c.bm from Yhb a,PubXtglYhjsgxbDO b,PubXtglJsbDO c " +
                    "where a.yhbh=b.yhbh and b.jsbh=c.jsbh and c.js='庭长' and a.yhbh="+yh.getYhbh();
            List<String> bm = aJlbDao.getList(sql,String.class,null);
            String hql = "select ggb from Ggb ggb,Yhb yh where yh.yhmc=ggb.fbr and ggb.status=6";
            if(bm.size()!=0){
                hql += " and";
            }
            for (int i = 0; i <bm.size() ; i++) {
                if(i==0&&i==bm.size()-1){
                    hql+=" (yh.yhbm='"+bm.get(i)+"')";
                }
                if(i==0&&i!=bm.size()-1){
                    hql+=" (yh.yhbm='"+bm.get(i)+"'";
                }
                if(i!=0&&i!=bm.size()-1){
                    hql +=" or yh.yhbm='"+bm.get(i)+"'";
                }
                if(i!=0&&i==bm.size()-1){
                    hql +=" or yh.yhbm='"+bm.get(i)+"')";
                }
            }
            List<Ggb> ggbList = aJlbDao.getList(hql,Ggb.class,pageRequest);
            _list = new PageImpl<>(ggbList,pageRequest,ggbList.size());
        }else {
            //用户身份为管理员
            _list = ggDao.findByStatus(pageRequest);
        }
        List<GgxqModel> list = new ArrayList<>();
        for (int i = 0; i <_list.getContent().size() ; i++) {
            GgxqModel ggxqModel = new GgxqModel(_list.getContent().get(i));
            list.add(ggxqModel);
        }
        return new PageImpl<>(list);
    }

    @Override
    public Map<Integer,List<Ggb>> getGgnrList() {
        //获得开庭公告
        Map<Integer,List<Ggb>> map = new LinkedHashMap<>();
        List<Ggb> ktggs = ggDao.getKtgg();
        /**
         * 必须将开庭公告（gglx=2）的公告放在最前面
         */
        map.put(2,ktggs);

        //获得其他类型的公告，并将其分类
        List<Ggb> _list = ggDao.getFgGgbs();
        for (int i = 0; i <_list.size() ; i++) {
            Ggb gg = _list.get(i);
            List<Ggb> ggbList = map.get(gg.getGglx());
            if(ggbList==null){
                ggbList = new ArrayList<>();
            }
            ggbList.add(gg);
            map.put(gg.getGglx(),ggbList);
        }
        return map;
    }

    @Override
    public Page<Ggb> getGgControllist(PageRequest pageRequest) {
        return ggDao.getGgListControl(pageRequest);
    }


}
