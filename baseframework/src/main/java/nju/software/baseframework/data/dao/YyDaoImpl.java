package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.vo.YyxqModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by 28643 on 2018/12/7.
 */
public class YyDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

/*
*
* @Query(value = "select new nju.software.baseframework.data.vo.YyxqModel(yyb.ajxh,yyb.bh,yyb.yyzt,yyb.yysj,yyb.lfsj,yyb.jfdd,yyb.jfr,yyb.jfbm,yyb.jfrlxfs,yyb.lfr,yyb.lfrsfzh,yyb.lfrlxfs,yyb.bz,yyb.lfsy,yyb.opers) " +
            " from Yyb yyb left join AJjb ajjb on yyb.ajxh = ajjb.ajxh ")
   Page<YyxqModel> findAll(Pageable pageable);
* */
   @SuppressWarnings("unchecked")
    public Page<YyxqModel> findAllYyxx(Pageable pageable) {
        return null;
    }
}
