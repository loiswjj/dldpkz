package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Yyb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YyDao extends JpaRepository<Yyb, Integer>,
        JpaSpecificationExecutor<Yyb> {
    Yyb findByBh(Integer bh);

    List<Yyb> findByAh(String ah);

    @Query(value = "select yyb from Yyb yyb where yyb.ah=?1")
    List<Yyb> findAllAjyy(String ah);

    @Query(value = "select yyb from Yyb yyb where yyb.ah=?1 and yyb.yyzt=?2")
    List<Yyb> findAjyy(String ah,Integer yyzt);

    /***
     * 找出需要审核的预约数据
     * @return
     */
    @Query(value = "select yyb from Yyb yyb where yyb.yyzt=5")
    Page<Yyb> findByYyzt(Pageable pageable);

    @Query(value = "select yyzt,count(BH) as num from pub_yyb group by yyzt",nativeQuery = true)
    List getYyChart();

    @Query(value = "select max(bh) from Yyb where ah=?1")
    Integer getYybByAh(String ah);
}
