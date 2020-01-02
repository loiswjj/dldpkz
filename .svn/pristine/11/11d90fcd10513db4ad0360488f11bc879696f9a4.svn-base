package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Ggxx;
import nju.software.baseframework.data.vo.GgxxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by johnl on 2019/1/17.
 */
@Repository
public interface GgxxDao extends JpaRepository<Ggxx,Integer> {
    @Query(value = "select new nju.software.baseframework.data.vo.GgxxModel(ggxx.ajxh,ggxx.ah,ggxx.fymc,ggxx.lar,ggxx.ggnr,dmb.ggmc,ggxx.fbsj) " +
            "from Ggxx ggxx,GgxxDmb dmb where ggxx.fybh = ?1 and ggxx.gglbid = dmb.gglbid and ggxx.gyStatus > 0 and ggxx.fbsj between ?2 and ?3")
    List<GgxxModel> getGgxxs(String dqfy, Date startTime, Date endTime);
}
