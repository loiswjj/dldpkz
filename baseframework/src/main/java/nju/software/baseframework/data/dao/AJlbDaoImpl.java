package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.vo.GGLXEnum;
import nju.software.baseframework.data.vo.GgTjModel;
import nju.software.baseframework.data.vo.yyTjModel;
import nju.software.baseframework.util.DateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class AJlbDaoImpl implements AJlbDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected Session getCurrentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public <T> List<T> getList(String hql,Class<T> cls,PageRequest pageRequest) {
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        if(pageRequest!=null){
            query.setFirstResult(pageRequest.getPageNumber());
            query.setMaxResults(pageRequest.getPageSize());
        }
        List<T> list = query.list();
        return list;
    }

    @Override
    public long getCount(String sql) {
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        long count = (long) query.uniqueResult();
        return count;
    }

    @Override
    public List<GgTjModel> ggtj() {
        String sql = "select fbr,fbsj,gglx,count(bh) from Ggb group by fbr,date_format(fbsj,'%y%m%d')";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        List list = query.list();
        List<GgTjModel> ggTjModels = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            Object[] object = (Object[])list.get(i);
            String fbr = (String)object[0];
            String fbsj = DateUtil.format((Date)object[1],DateUtil.shortFormat);
            String gglx = GGLXEnum.getExplainByNumber(
                    Integer.toString((Integer)object[2]));
            long count = (long)object[3];
            GgTjModel model = new GgTjModel(fbr,fbsj,gglx,count);
            ggTjModels.add(model);
        }
        return ggTjModels;
    }

    @Override
    public List<yyTjModel> yytj() {
        String sql = "select jfr,yysj,count(bh) from Yyb group by jfr,date_format(yysj,'%y%m%d')";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        List list = query.list();
        List<yyTjModel> yyTjModels = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] object = (Object[])list.get(i);
            String jfr = (String)object[0];
            String yysj = DateUtil.format((Date)object[1],DateUtil.shortFormat);
            long bh = (long)object[2];
            yyTjModel model = new yyTjModel(jfr,yysj,bh);
            yyTjModels.add(model);
        }
        return yyTjModels;
    }

    @Override
    public String getSfzh(String ah,String lfr) {
        String sql = "select dsr.DSRLB,dsr.AJXH from DSR_JB dsr,PUB_AJ_JB ajjb where" +
                " ajjb.AJXH=dsr.AJXH and dsr.DSRJC='"+lfr+"' and ajjb.AH='"+ah+"'";
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql);
        Object[] object = (Object[]) query.uniqueResult();
        String lb = (String) object[0];
        Integer ajxh = (Integer) object[1];
        String sfzhm = null;
        if(lb.equals("1")||lb.equals("2")){
            sql = "select SFZHM from DSR_GR where AJXH="+ajxh+" and XM='"+lfr+"'";
        }else {
            sql = "select DJZH from DSR_DW where AJXH="+ajxh+" and DWMC='"+lfr+"'";
        }
        query = session.createSQLQuery(sql);
        sfzhm = (String) query.uniqueResult();
        return sfzhm.trim();
    }
}
