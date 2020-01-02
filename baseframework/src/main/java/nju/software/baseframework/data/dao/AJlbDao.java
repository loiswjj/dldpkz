package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.vo.GgTjModel;
import nju.software.baseframework.data.vo.yyTjModel;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AJlbDao {
    /**
     * 获取list模板
     * @param hql
     * @param cls
     * @param pageRequest
     * @param <T>
     * @return
     */
    <T> List<T> getList(String hql,Class<T> cls,PageRequest pageRequest);

    long getCount(String sql);

    List<GgTjModel> ggtj();

    List<yyTjModel> yytj();

    String getSfzh(String ah,String lfr);
}
