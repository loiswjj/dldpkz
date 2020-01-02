package nju.software.baseframework.service.impl;

import nju.software.baseframework.convertor.SearchConverter;
import nju.software.baseframework.data.dao.AJlbDao;
import nju.software.baseframework.data.dao.YyDao;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.dataobject.Yyb;
import nju.software.baseframework.data.vo.TableModel;
import nju.software.baseframework.data.vo.YyChartModel;
import nju.software.baseframework.data.vo.YyxqModel;
import nju.software.baseframework.service.YyService;
import nju.software.baseframework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static nju.software.baseframework.util.DateUtil.parseDate;

/**
 * Created by 28643 on 2018/11/28.
 */
@Service
@Transactional
public class YyServiceImpl implements YyService {
    @Autowired
    YyDao yyDao;

    @Autowired
    AJlbDao aJlbDao;

    @Override
    public Yyb findByBh(Integer bh) {
        return yyDao.findByBh(bh);
    }

    @Override
    public TableModel<YyxqModel> SearchYylb(String ah,String ajmc,String lfr,
                                            String jfdd,String lxfs,String lfsj,
                                            Integer yyzt,
                                            Yhb yh, PageRequest pageRequest) {
        String sql = SearchConverter.getYySql(ah,ajmc,lfr,jfdd,lxfs,lfsj,yyzt,yh,0);
        List<Yyb> yyb = aJlbDao.getList(sql,Yyb.class,pageRequest);
        String sql2 = SearchConverter.getYySql(ah,ajmc,lfr,jfdd,lxfs,lfsj,yyzt,yh,1);
        long total = aJlbDao.getCount(sql2);
        List<YyxqModel> yyxqModels = new ArrayList<>();
        for (int i = 0; i <yyb.size() ; i++) {
            YyxqModel model = new YyxqModel(yyb.get(i));
            yyxqModels.add(model);
        }
        TableModel<YyxqModel> res = new TableModel<>(total,yyxqModels);
        return res;
    }

    @Override
    public List<YyxqModel> getYylb(String ah, String ajmc, String lfr, String jfdd, String lxfs, String lfsj, Integer yyzt, Yhb yh) {
        String sql = SearchConverter.getYySql(ah,ajmc,lfr,jfdd,lxfs,lfsj,yyzt,yh,0);
        List<Yyb> yyb = aJlbDao.getList(sql,Yyb.class,null);
        List<YyxqModel> yyxqModels = new ArrayList<>();
        for (int i = 0; i <yyb.size() ; i++) {
            YyxqModel model = new YyxqModel(yyb.get(i));
            yyxqModels.add(model);
        }
        return yyxqModels;
    }

    @Override
    public YyxqModel getYyxxByBhAndAJXH(Integer bh) {
        Yyb yyb=  yyDao.findByBh(bh);
        //通过案件序号 查询到 本案当事人和本案关系人；
        YyxqModel res = new YyxqModel(yyb);
        return res;
    }

    @Override
    public TableModel<YyxqModel> getTodayData(String jfr,PageRequest pageRequest) {
        //如果是管理员则显示出所有的今日预约
        String yysj = DateUtil.format(new Date(),DateUtil.shortFormat);
        String hql1 = "select yyb from Yyb yyb where yyb.jfr='"
                +jfr+"' and yyb.yysj='"+yysj+"'";
        String hql2 = "select count(yyb) from Yyb yyb where yyb.jfr='"
                +jfr+"' and yyb.yysj='"+yysj+"'";
        if(jfr=="系统管理员"){
            hql1 = "select yyb from Yyb yyb where yyb.yysj='"+yysj+"'";
            hql2 = "select count(yyb) from Yyb yyb where yyb.yysj='"+yysj+"'";
        }
        List<Yyb> _list = aJlbDao.getList(hql1,Yyb.class,pageRequest);
        List<YyxqModel> yybs = new ArrayList<>();
        for (int i = 0; i < _list.size(); i++) {
            Yyb yyb = _list.get(i);
            yybs.add(new YyxqModel(yyb));
        }
        long count = aJlbDao.getCount(hql2);
        TableModel<YyxqModel> tableModel = new TableModel<>(count,yybs);
        return tableModel;
    }

    @Override
    public List<Yyb> getAjYy(String ah, Integer yyzt) {
        if(yyzt==null||yyzt==5){
            return yyDao.findAllAjyy(ah);
        }else {
            return yyDao.findAjyy(ah,yyzt);
        }
    }

    @Override
    public List<Long> getSjtj() {
        String yysj = DateUtil.format(new Date(),DateUtil.shortFormat);
        String zxly = "select count(yyb) from Yyb yyb where yyb.yysj='"+yysj+"'";
        String zly = "select count(yyb) from Yyb yyb";
        String zzfb = "select count(ggb) from Ggb ggb where ggb.status=1";
        String zgg = "select count(ggb) from Ggb ggb";
        long zx = aJlbDao.getCount(zxly);
        long ls = aJlbDao.getCount(zly)-zx;
        long fb = aJlbDao.getCount(zzfb);
        long js = aJlbDao.getCount(zgg)-fb;
        List<Long> list = new ArrayList<>();
        list.add(zx);
        list.add(ls);
        list.add(fb);
        list.add(js);
        return list;
    }

    @Override
    public Page<YyxqModel> getYySh(PageRequest pageRequest) {
        Page<Yyb> _list = yyDao.findByYyzt(pageRequest);
        List<YyxqModel> list = new ArrayList<>();
        for (int i = 0; i <_list.getContent().size() ; i++) {
            list.add(new YyxqModel(_list.getContent().get(i)));
        }
        return new PageImpl<>(list);
    }

    @Override
    @Cacheable(cacheNames = "getbh")
    public Integer getYybByAh(String ah) {
        return yyDao.getYybByAh(ah);
    }

    @Override
    public List<YyChartModel> getYyChart(String spt, String ajxz, String jafs,
                           String cbr, String begin, String last) {
        String sql = SearchConverter.sjtjSql(spt,ajxz,jafs,cbr,begin,last,1);
        List<YyChartModel> _list = aJlbDao.getList(sql,YyChartModel.class,null);
        return _list;
    }
}