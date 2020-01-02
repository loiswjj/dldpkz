package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.DsrJb;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 28643 on 2018/12/3.
 */
@Repository
public interface DsrDao extends JpaRepository<DsrJb,Integer> {

    @Cacheable(cacheNames = "findDsr")
    List<DsrJb> findByAjxh(Integer ajxh);
}
