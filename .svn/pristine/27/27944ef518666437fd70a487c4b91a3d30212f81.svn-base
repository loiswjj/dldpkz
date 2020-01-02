package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Yhb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YhbDao extends JpaRepository<Yhb,Integer>,JpaSpecificationExecutor<Yhb> {
    /**
     * 通过用户名称找到用户
     * @param yhdm
     * @return
     */
    Yhb findByYhdm(String yhdm);

    @Query(value = "select yhb from Yhb yhb")
    Page<Yhb> findAll(Pageable pageable);

    /**
     * 通过用户编号找到用户
     * @param yhbh
     * @return
     */
    Yhb findByYhbh(Integer yhbh);

    @Query(value = "select yhqx from Yhb where yhdm=?1")
    Integer getYhxq(String yhdm);
}
