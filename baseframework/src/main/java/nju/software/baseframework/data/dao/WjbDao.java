package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Wjb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface WjbDao extends JpaRepository<Wjb,Integer> {

    @Query(value = "select wj from Wjb wj where wj.lx=?1")
    Page<Wjb> findByType(Pageable pageable,Integer lx);

    @Query(value = "select wj from Wjb wj where wj.type='doc'")
    Page<Wjb> findWjbByType(Pageable pageable);

    @Query(value = "select bcwz from Wjb where id=?1")
    String findBcwz(Integer id);

    @Query(value = "select wj from Wjb wj where wj.zt=1 and wj.lx=?1")
    List<Wjb> findBcwzList(Integer lx);

    @Query(value = "select wj from Wjb wj where wj.id=?1")
    Wjb findWjbById(Integer id);
}
