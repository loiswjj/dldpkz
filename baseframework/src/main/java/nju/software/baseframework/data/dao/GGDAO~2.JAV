package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Ggb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 28643 on 2018/12/6.
 */
public interface GgDao extends JpaRepository<Ggb, Integer>,
        JpaSpecificationExecutor<Ggb> {
    List<Ggb> findByAh(String ah);

    @Query(value = "select ggb from Ggb ggb where ggb.bh=?1")
    Ggb findBybh(Integer id);

    @Query(value = "select max(ggb.bh) from Ggb ggb where ggb.ah=?1 and ggb.gglx=?2")
    Integer findByAhAndGglx(String ah,Integer gglx);

    @Query(value = "select ggb from Ggb ggb where ggb.ah=?1")
    List<Ggb> findAllAjgg(String ah);

    @Query(value = "select ggb from Ggb ggb where ggb.ah=?1 and ggb.status=?2")
    List<Ggb> findAjgg(String ah,Integer ggzt);

    /**
     * 找出需要审核的公告数据
     * @return
     *
     */
    @Query(value = "select ggb from Ggb ggb where ggb.status=6")
    Page<Ggb> findByStatus(Pageable pageable);

    /**
     * 或得数据统计中公告图表的数据
     */
    @Query(value = "select gglx,count(BH) as num from pub_ggb group by gglx",nativeQuery = true)
    List getGgChart();

    @Query(value = "select ggb from Ggb ggb where (ggb.status=1 or ggb.status=2)")
    Page<Ggb> getGgListControl(Pageable pageable);

    @Query(value = "select * from pub_ggb where SFBF=1 and (STATUS=1 or STATUS=2) and DATEDIFF(KTSJ,now())<=7 and KTSJ>=now()",nativeQuery = true)
    List<Ggb> getKtgg();

    /**
     * 获得法官发布的公告
     * @return
     */
    @Query(value = "SELECT * FROM pub_ggb WHERE (STATUS = 1 or STATUS=2) AND GGLX != 2 AND DateDiff(FBSJ,now()) <= GGSC",nativeQuery = true)
    List<Ggb> getFgGgbs();

    Ggb findByAjxh(int ajxh);
}
