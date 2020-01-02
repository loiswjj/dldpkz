package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.GnbDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GnbDao extends JpaRepository<GnbDO, Integer>,
        JpaSpecificationExecutor<GnbDO> {
    @Query(value = "select gnb from GnbDO gnb")
    Page<GnbDO> getAll(Pageable pageable);

    @Query(value = "select gnb from GnbDO gnb where gnb.bh=?1")
    GnbDO getGnbDOByBh(Integer bh);

    /**
     * 获得公告审核的标准
     * @return
     */
    @Query(value = "select gnms from GnbDO  where lbbh=1 and status=1")
    String getGgGnms();
}
